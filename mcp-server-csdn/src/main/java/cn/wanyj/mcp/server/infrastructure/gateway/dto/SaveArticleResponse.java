package cn.wanyj.mcp.server.infrastructure.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CSDN保存文章响应DTO
 * @author 永
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaveArticleResponse {
    private Integer code;
    private String traceId;
    private SaveArticleResponseData data;
    private String msg;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveArticleResponseData {
        private String url;
        private Integer article_id;
        private String title;
        private String description;
    }
}