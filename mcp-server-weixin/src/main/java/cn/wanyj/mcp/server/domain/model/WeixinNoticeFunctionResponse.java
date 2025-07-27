package cn.wanyj.mcp.server.domain.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;

/**
 * @author 永
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeixinNoticeFunctionResponse {
    @JsonProperty(required = true, value = "success")
    @JsonPropertyDescription("是否成功")
    private boolean success;
}
