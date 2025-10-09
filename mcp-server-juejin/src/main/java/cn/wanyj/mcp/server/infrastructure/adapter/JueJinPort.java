package cn.wanyj.mcp.server.infrastructure.adapter;

import com.alibaba.fastjson.JSON;
import cn.wanyj.mcp.server.domain.adapter.IJueJinPort;
import cn.wanyj.mcp.server.domain.model.ArticleFunctionRequest;
import cn.wanyj.mcp.server.domain.model.ArticleFunctionResponse;
import cn.wanyj.mcp.server.infrastructure.gateway.IJueJinService;
import cn.wanyj.mcp.server.infrastructure.gateway.dto.JueJinCreateRequest;
import cn.wanyj.mcp.server.infrastructure.gateway.dto.JueJinCreateResponse;
import cn.wanyj.mcp.server.infrastructure.gateway.dto.JueJinPublishRequest;
import cn.wanyj.mcp.server.infrastructure.gateway.dto.JueJinPublishResponse;
import cn.wanyj.mcp.server.type.properties.JueJinApiProperties;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**
 * @author 永
 */
@Slf4j
@Component
public class JueJinPort implements IJueJinPort {

    @Resource
    private IJueJinService juejinService;

    @Resource
    private JueJinApiProperties juejinApiProperties;

    @Override
    public ArticleFunctionResponse writeArticleOfJueJin(ArticleFunctionRequest request) throws IOException {
        // 创建文章
        JueJinCreateRequest articleRequestDTO = new JueJinCreateRequest();
        articleRequestDTO.setTitle(request.getTitle());
        articleRequestDTO.setMark_content(request.getMarkdowncontent());
        if (request.getMarkdowncontent().length() > 50) {
            articleRequestDTO.setBrief_content(request.getMarkdowncontent().substring(0, 70)); // 取前70个字符 （要求：50-100）
        }

        Call<JueJinCreateResponse> call = juejinService.createArticle(juejinApiProperties.getCookie(), juejinApiProperties.getAuthorization(), articleRequestDTO);

        Response<JueJinCreateResponse> response = call.execute();

        log.info("请求JueJin创建文章 \nreq:{} \nres:{}", JSON.toJSONString(articleRequestDTO), JSON.toJSONString(response));

        if (!response.isSuccessful()) {
            return null;
        }

        JueJinCreateResponse articleResponseDTO = response.body();
        if (articleResponseDTO == null) {
            return null;
        }

        ArticleFunctionResponse articleFunctionResponse = new ArticleFunctionResponse();
        articleFunctionResponse.setCode(articleResponseDTO.getErr_no());
        articleFunctionResponse.setMsg(articleResponseDTO.getErr_msg());

        // 发送文章
        JueJinPublishRequest jueJinPublishRequest = new JueJinPublishRequest();
        jueJinPublishRequest.setDraft_id(articleResponseDTO.getData().getId());
        Call<JueJinPublishResponse> jueJinPublishResponseCall = juejinService.publishArticle(juejinApiProperties.getCookie(), juejinApiProperties.getAuthorization(), jueJinPublishRequest);
        Response<JueJinPublishResponse> jueJinPublishResponse = jueJinPublishResponseCall.execute();
        if (!jueJinPublishResponse.isSuccessful()) {
            return null;

        }

        JueJinPublishResponse jueJinPublishResponseBody = jueJinPublishResponse.body();
        if (jueJinPublishResponseBody != null) {
            articleFunctionResponse.setCode(jueJinPublishResponseBody.getErr_no());
            articleFunctionResponse.setMsg(jueJinPublishResponseBody.getErr_msg());
            articleFunctionResponse.setUrl("https://juejin.cn/post/"+jueJinPublishResponseBody.getData().getArticle_id());
        }
        return articleFunctionResponse;
    }

}

