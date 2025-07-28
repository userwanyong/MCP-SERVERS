package cn.wanyj.mcp.server.type.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 永
 */
@Data
@ConfigurationProperties(prefix = "weixin.api")
@Component
public class WeixinApiProperties {
    private String original_id;
    private String app_id;
    private String app_secret;
    private String template_id;
    private String touser;
}
