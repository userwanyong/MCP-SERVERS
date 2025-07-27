package cn.wanyj.mcp.server.infrastructure.gateway;


import cn.wanyj.mcp.server.infrastructure.gateway.dto.WeixinTemplateMsgRequestDTO;
import cn.wanyj.mcp.server.infrastructure.gateway.dto.WeixinTokenResponseDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author 永
 */
public interface IWeixinService {
    /**
     * 获取 Access token
     * GET https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
     * 文档：<a href="https://developers.weixin.qq.com/doc/offiaccount/Basic_Information/Get_access_token.html">
     * @param grantType 获取 access_token 填写 client_credential
     * @param appId     第三方用户唯一凭证
     * @param appSecret 第三方用户唯一凭证密钥，即 appsecret
     * @return 响应结果
     */
    @GET("cgi-bin/token")
    Call<WeixinTokenResponseDTO> getAccessToken(@Query("grant_type") String grantType, @Query("appid") String appId, @Query("secret") String appSecret);



    /**
     * 发送微信公众号模板消息
     * POST https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN
     * 文档：<a href="https://mp.weixin.qq.com/debug/cgi-bin/readtmpl?t=tmplmsg/faq_tmpl">
     *
     * @param accessToken              getAccessToken 获取的 token 信息
     * @param weixinTemplateMsgRequestDTO 入参对象
     * @return 应答结果
     */
    @POST("cgi-bin/message/template/send")
    Call<Void> sendMessage(@Query("access_token") String accessToken, @Body WeixinTemplateMsgRequestDTO weixinTemplateMsgRequestDTO);

}
