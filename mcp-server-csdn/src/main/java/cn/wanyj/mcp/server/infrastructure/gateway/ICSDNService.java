package cn.wanyj.mcp.server.infrastructure.gateway;

import cn.wanyj.mcp.server.infrastructure.gateway.dto.SaveArticleRequest;
import cn.wanyj.mcp.server.infrastructure.gateway.dto.SaveArticleResponse;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * CSDN API服务接口
 * @author 永
 */
public interface ICSDNService {
    
    /**
     * 保存文章接口
     * 
     * @param cookie Cookie信息
     * @param request 请求参数
     * @return 保存文章响应
     */
    @Headers({
            "accept: application/json, text/plain, */*",
            "accept-language: zh-CN,zh;q=0.9",
            "content-type: application/json;",
            "origin: https://mpbeta.csdn.net",
            "priority: u=1, i",
            "referer: https://mpbeta.csdn.net/",
            "sec-ch-ua: \"Chromium\";v=\"134\", \"Not:A-Brand\";v=\"24\", \"Google Chrome\";v=\"134\"",
            "sec-ch-ua-mobile: ?0",
            "sec-ch-ua-platform: \"Windows\"",
            "sec-fetch-dest: empty",
            "sec-fetch-mode: cors",
            "sec-fetch-site: same-site",
            "user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36",
            "X-CA-Key: 203803574",
            "X-CA-Nonce: fc493b10-1c23-439c-abbb-6cb5d9cfea16",
            "X-CA-Signature: NSCHCXpHlBS/rr0e1Vy7SYf7b5BYebwuRkOgFar3rso=",
            "X-CA-Signature-Headers: x-ca-key,x-ca-nonce"
    })
    @POST("blog-console-api/v1/postedit/saveArticle")
    Call<SaveArticleResponse> saveArticle(
            @Header("Cookie") String cookie,
            @Body SaveArticleRequest request
    );
}