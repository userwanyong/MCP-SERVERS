package cn.wanyj.mcp.server.infrastructure.gateway.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 掘金创建文章请求DTO
 * @author 永
 */
@Data
public class JueJinCreateRequest {
    private String category_id="6809637769959178254";
    private List<String> tag_ids= Collections.singletonList("6809640408797167623");
    private String link_url="";
    private String cover_image="";
    private String title;
    private String brief_content="11111111111111111111111111111111111111111111111111111111111111111111111111111111";
    private Integer edit_type=10;
    private String html_content="deprecated";
    private String mark_content;
    private List<String> theme_ids=new ArrayList<>();
    private List<String> pics=new ArrayList<>();
}