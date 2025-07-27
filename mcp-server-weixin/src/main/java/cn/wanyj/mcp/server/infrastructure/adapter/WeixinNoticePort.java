package cn.wanyj.mcp.server.infrastructure.adapter;


import cn.wanyj.mcp.server.domain.adapter.IWeixinNoticePort;
import cn.wanyj.mcp.server.domain.model.WeixinNoticeFunctionRequest;
import cn.wanyj.mcp.server.domain.model.WeixinNoticeFunctionResponse;
import cn.wanyj.mcp.server.infrastructure.gateway.IWeixinService;
import cn.wanyj.mcp.server.infrastructure.gateway.dto.WeixinTemplateMsgRequestDTO;
import cn.wanyj.mcp.server.infrastructure.gateway.dto.WeixinTokenResponseDTO;
import cn.wanyj.mcp.server.type.properties.WeixinApiProperties;
import com.google.common.cache.Cache;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import retrofit2.Call;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 永
 */
@Slf4j
@Component
public class WeixinNoticePort implements IWeixinNoticePort {

    @Resource
    private WeixinApiProperties properties;

    @Resource
    private IWeixinService weixinService;

    @Resource
    private Cache<String, String> weixinAccessToken;

    @Override
    public WeixinNoticeFunctionResponse sendMsg(WeixinNoticeFunctionRequest request) throws IOException {
        // 1. 获取 accessToken
        String accessToken = weixinAccessToken.getIfPresent(properties.getAppid());
        if (accessToken==null) {
            Call<WeixinTokenResponseDTO> call = weixinService.getAccessToken("client_credential", properties.getAppid(), properties.getAppsecret());
            WeixinTokenResponseDTO weixinTokenResponseDTO = call.execute().body();
            assert weixinTokenResponseDTO != null;
            accessToken = weixinTokenResponseDTO.getAccess_token();
            weixinAccessToken.put(properties.getAppid(), accessToken);
        }

        // 2. 发送模板消息
        Map<String, Map<String, String>> data = new HashMap<>();
        WeixinTemplateMsgRequestDTO.put(data, WeixinTemplateMsgRequestDTO.TemplateKey.PLATFORM, request.getPlatform());
        WeixinTemplateMsgRequestDTO.put(data, WeixinTemplateMsgRequestDTO.TemplateKey.SUBJECT, request.getSubject());
        WeixinTemplateMsgRequestDTO.put(data, WeixinTemplateMsgRequestDTO.TemplateKey.DESCRIPTION, request.getDescription());

        WeixinTemplateMsgRequestDTO templateMessageDTO = new WeixinTemplateMsgRequestDTO(properties.getTouser(), properties.getTemplate_id());
        templateMessageDTO.setUrl(request.getJumpUrl());
        templateMessageDTO.setData(data);

        Call<Void> call = weixinService.sendMessage(accessToken, templateMessageDTO);
        call.execute();

        WeixinNoticeFunctionResponse weiXinNoticeFunctionResponse = new WeixinNoticeFunctionResponse();
        weiXinNoticeFunctionResponse.setSuccess(true);

        return weiXinNoticeFunctionResponse;
    }
}
