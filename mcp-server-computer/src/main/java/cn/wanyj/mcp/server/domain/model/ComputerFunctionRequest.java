package cn.wanyj.mcp.server.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;

/**
 * @author 永
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL) // 序列化时忽略值为 null 的字段
public class ComputerFunctionRequest {

    @JsonProperty(required = true, value = "computer")
    @JsonPropertyDescription("电脑名称")
    private String computer;

}
