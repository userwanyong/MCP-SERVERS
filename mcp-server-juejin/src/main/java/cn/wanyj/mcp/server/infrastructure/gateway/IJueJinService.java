package cn.wanyj.mcp.server.infrastructure.gateway;

import cn.wanyj.mcp.server.infrastructure.gateway.dto.JueJinCreateRequest;
import cn.wanyj.mcp.server.infrastructure.gateway.dto.JueJinCreateResponse;
import cn.wanyj.mcp.server.infrastructure.gateway.dto.JueJinPublishRequest;
import cn.wanyj.mcp.server.infrastructure.gateway.dto.JueJinPublishResponse;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * 掘金API服务接口
 * @author 永
 */
public interface IJueJinService {
    
    /**
     * 创建文章
     * 
     * @param cookie Cookie信息
     * @param authorization 授权信息
     * @param request 创建请求
     * @return 创建响应
     */
    @POST("content_api/v1/article_draft/create")
    Call<JueJinCreateResponse> createArticle(
            @Header("Cookie") String cookie,
            @Header("authorization") String authorization,
            @Body JueJinCreateRequest request
    );
    
    /**
     * 发布文章
     * 
     * @param cookie Cookie信息
     * @param authorization 授权信息
     * @param request 发布请求
     * @return 发布响应
     */
    @POST("content_api/v1/article/publish")
    Call<JueJinPublishResponse> publishArticle(
            @Header("Cookie") String cookie,
            @Header("authorization") String authorization,
            @Body JueJinPublishRequest request
    );
}