package cn.wanyj.mcp.server.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 永
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleFunctionResponse {

    @JsonProperty(required = true, value = "code")
    @JsonPropertyDescription("code")
    private Integer code;

    @JsonProperty(required = true, value = "msg")
    @JsonPropertyDescription("msg")
    private String msg;

    @JsonProperty(required = true, value = "articleData")
    @JsonPropertyDescription("articleData")
    private SaveArticleResponseData articleData;

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class SaveArticleResponseData {
        @JsonProperty(required = true, value = "url")
        @JsonPropertyDescription("url")
        private String url;

        @JsonProperty(required = true, value = "article_id")
        @JsonPropertyDescription("article_id")
        private Integer article_id;

        @JsonProperty(required = true, value = "title")
        @JsonPropertyDescription("title")
        private String title;

        @JsonProperty(required = true, value = "description")
        @JsonPropertyDescription("description")
        private String description;
    }

}

