package cn.wanyj.mcp.server.test;

import cn.wanyj.mcp.server.infrastructure.gateway.IWeixinService;
import cn.wanyj.mcp.server.infrastructure.gateway.dto.WeixinTemplateMsgRequestDTO;
import cn.wanyj.mcp.server.infrastructure.gateway.dto.WeixinTokenResponseDTO;
import cn.wanyj.mcp.server.type.properties.WeixinApiProperties;
import com.alibaba.fastjson2.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Call;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class APITest {

    @Resource
    private WeixinApiProperties weixinApiProperties;
    @Resource
    private IWeixinService weixinService;

    private String accessToken;

    @Before
    public void before() throws IOException {
        Call<WeixinTokenResponseDTO> call = weixinService.getAccessToken("client_credential", weixinApiProperties.getAppid(), weixinApiProperties.getAppsecret());
        WeixinTokenResponseDTO weixinTokenResponseDTO = call.execute().body();
        assert weixinTokenResponseDTO != null;
        accessToken = weixinTokenResponseDTO.getAccess_token();
        log.info("weixin accessToken:{}", accessToken);
    }

    @Test
    public void test_template_message() throws IOException {
        Map<String, Map<String, String>> data = new HashMap<>();
        WeixinTemplateMsgRequestDTO.put(data, WeixinTemplateMsgRequestDTO.TemplateKey.PLATFORM, "CSDN");
        WeixinTemplateMsgRequestDTO.put(data, WeixinTemplateMsgRequestDTO.TemplateKey.SUBJECT, "Java求职面试：从Spring Boot到微服务的技术探索");
        WeixinTemplateMsgRequestDTO.put(data, WeixinTemplateMsgRequestDTO.TemplateKey.DESCRIPTION, "面试官是一位经验丰富且严肃的技术大牛，而谢飞机则是以幽默著称的“水货”程序员。：Elasticsearch用于存储，Logstash用于日志收集和传输。");

        WeixinTemplateMsgRequestDTO templateMessageDTO = new WeixinTemplateMsgRequestDTO(weixinApiProperties.getTouser(), weixinApiProperties.getTemplate_id());
        templateMessageDTO.setUrl("https://blog.csdn.net/2401_86434325/article/details/149690686");
        templateMessageDTO.setData(data);

        Call<Void> call = weixinService.sendMessage(accessToken, templateMessageDTO);
        call.execute();

        log.info("请求参数:{}", JSON.toJSONString(templateMessageDTO));
    }


}
