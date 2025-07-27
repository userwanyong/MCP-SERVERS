package cn.wanyj.mcp.server.infrastructure.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * CSDN保存文章请求DTO
 * @author 永
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaveArticleRequest {
    private String article_id;
    private String title;
    private String description;
    private String content;
    private String tags;
    private String categories = "";
    private String type = "original";
    private Integer status = 0;
    private String read_type = "public";
    private String reason = "";
    private String original_link = "";
    private Boolean authorized_status=false;
    private Boolean check_original=false;
    private String source="pc_postedit";
    private Integer not_auto_saved=1;
    private String creator_activity_id="";
    private List<String> cover_images=new ArrayList<>();
    private Integer cover_type=1;
    private Integer vote_id=0;
    private String resource_id="";
    private Integer scheduled_time=0;
    private String markdowncontent="";
    private String resource_url="";
    private Integer editor_type=0;
    private List<String> plan= Collections.emptyList();
    private String level="0";
    private Integer is_new=1;
    private Integer sync_git_code=0;
}