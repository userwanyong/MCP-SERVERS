package cn.wanyj.mcp.server.infrastructure.gateway.dto;

import lombok.Data;

import java.util.List;

/**
 * 掘金发布文章响应DTO
 * @author 永
 */
@Data
public class JueJinPublishResponse {
    private Integer err_no;
    private String err_msg;
    private ArticleData data;

    @Data
    public static class ArticleData {
        private String article_id;
        private String user_id;
        private String category_id;
        private List<String> tag_ids;
        private Integer visible_level;
        private String link_url;
        private String cover_image;
        private Integer is_gfw;
        private String title;
        private String brief_content;
        private Integer is_english;
        private Integer is_original;
        private Integer user_index;
        private Integer original_type;
        private String original_author;
        private String content;
        private String ctime;
        private String mtime;
        private String rtime;
        private Integer status;
        private Integer verify_status;
        private Integer audit_status;
        private String mark_content;
        private String org_id;
        private Integer homepage_top_time;
        private Integer homepage_top_status;
    }
}