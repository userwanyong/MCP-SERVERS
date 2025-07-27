package cn.wanyj.mcp.server.infrastructure.gateway.dto;

import lombok.Data;

import java.util.List;

/**
 * 掘金创建文章响应DTO
 * @author 永
 */
@Data
public class JueJinCreateResponse {
    private Integer err_no;
    private String err_msg;
    private ArticleData data;

    @Data
    public static class ArticleData {
        private String id;
        private String article_id;
        private String user_id;
        private String category_id;
        private List<String> tag_ids;
        private String link_url;
        private String cover_image;
        private Integer is_gfw;
        private String title;
        private String brief_content;
        private Integer is_english;
        private Integer is_original;
        private Integer edit_type;
        private String html_content;
        private String mark_content;
        private String ctime;
        private String mtime;
        private Integer status;
        private Integer original_type;
        private List<String> theme_ids;
    }
}