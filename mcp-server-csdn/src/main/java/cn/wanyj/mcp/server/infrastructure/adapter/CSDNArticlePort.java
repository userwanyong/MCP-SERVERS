package cn.wanyj.mcp.server.infrastructure.adapter;

import com.alibaba.fastjson.JSON;
import cn.wanyj.mcp.server.domain.adapter.ICSDNArticlePort;
import cn.wanyj.mcp.server.domain.model.ArticleFunctionRequest;
import cn.wanyj.mcp.server.domain.model.ArticleFunctionResponse;
import cn.wanyj.mcp.server.infrastructure.gateway.ICSDNService;
import cn.wanyj.mcp.server.infrastructure.gateway.dto.SaveArticleRequest;
import cn.wanyj.mcp.server.infrastructure.gateway.dto.SaveArticleResponse;
import cn.wanyj.mcp.server.type.properties.CSDNApiProperties;
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
public class CSDNArticlePort implements ICSDNArticlePort {

    @Resource
    private ICSDNService csdnService;

    @Resource
    private CSDNApiProperties csdnApiProperties;

    @Override
    public ArticleFunctionResponse writeArticleOfCsdn(ArticleFunctionRequest request) throws IOException {

        SaveArticleRequest articleRequestDTO = new SaveArticleRequest();
        articleRequestDTO.setTitle(request.getTitle());
        articleRequestDTO.setMarkdowncontent(request.getMarkdowncontent());
        articleRequestDTO.setContent(request.getContent());
        articleRequestDTO.setTags(request.getTags());
        articleRequestDTO.setDescription(request.getDescription());
        articleRequestDTO.setCategories(csdnApiProperties.getCategories());

        Call<SaveArticleResponse> call = csdnService.saveArticle(csdnApiProperties.getCookie(), articleRequestDTO);

        Response<SaveArticleResponse> response = call.execute();

        log.info("请求CSDN发帖 \nreq:{} \nres:{}", JSON.toJSONString(articleRequestDTO), JSON.toJSONString(response));

        if (response.isSuccessful()) {
            SaveArticleResponse articleResponseDTO = response.body();
            if (articleResponseDTO == null) {
                return null;
            }
            SaveArticleResponse.SaveArticleResponseData data = articleResponseDTO.getData();

            ArticleFunctionResponse articleFunctionResponse = new ArticleFunctionResponse();
            articleFunctionResponse.setCode(articleResponseDTO.getCode());
            articleFunctionResponse.setMsg(articleResponseDTO.getMsg());
            articleFunctionResponse.setArticleData(ArticleFunctionResponse.SaveArticleResponseData.builder()
                    .url(data.getUrl())
                    .article_id(data.getArticle_id())
                    .title(data.getTitle())
                    .description(data.getDescription())
                    .build());
            return articleFunctionResponse;
        }

        return null;
    }

}

