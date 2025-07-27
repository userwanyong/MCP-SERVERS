package cn.wanyj.mcp.server.infrastructure.gateway.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 掘金发布文章请求DTO
 * @author 永
 */
@Data
public class JueJinPublishRequest {
    private String draft_id;
    private Boolean sync_to_org=false;
    private List<String> column_ids=new ArrayList<>();
    private List<String> theme_ids=new ArrayList<>();
    private Integer encrypted_word_count=1077879;
    private Integer origin_word_count=7;
}