package cn.wanyj.mcp.server.infrastructure.gateway.dto;


import lombok.Data;

/**
 * @author 永
 */
@Data
public class WeixinTokenResponseDTO {
    private String access_token;
    private int expires_in;
    private String errcode;
    private String errmsg;
}
