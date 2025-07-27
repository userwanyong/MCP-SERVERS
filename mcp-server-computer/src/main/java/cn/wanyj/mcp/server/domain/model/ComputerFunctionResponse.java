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
public class ComputerFunctionResponse {

    @JsonProperty(required = true, value = "操作系统名称")
    @JsonPropertyDescription("操作系统名称")
    private String osName;

    @JsonProperty(required = true, value = "操作系统版本")
    @JsonPropertyDescription("操作系统版本")
    private String osVersion;

    @JsonProperty(required = true, value = "操作系统架构")
    @JsonPropertyDescription("操作系统架构")
    private String osArch;

    @JsonProperty(required = true, value = "用户的账户名称")
    @JsonPropertyDescription("用户的账户名称")
    private String userName;

    @JsonProperty(required = true, value = "用户的主目录")
    @JsonPropertyDescription("用户的主目录")
    private String userHome;

    @JsonProperty(required = true, value = "用户的当前工作目录")
    @JsonPropertyDescription("用户的当前工作目录")
    private String userDir;

    @JsonProperty(required = true, value = "Java 运行时环境版本")
    @JsonPropertyDescription("Java 运行时环境版本")
    private String javaVersion;

    @JsonProperty(required = true, value = "系统信息")
    @JsonPropertyDescription("系统信息")
    private String osInfo;

}
