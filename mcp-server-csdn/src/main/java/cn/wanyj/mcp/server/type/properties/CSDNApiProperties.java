package cn.wanyj.mcp.server.type.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 永
 */
@Getter
@ConfigurationProperties(prefix = "csdn.api")
@Component
public class CSDNApiProperties {

    private String cookie;

    private String categories;

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }
}

