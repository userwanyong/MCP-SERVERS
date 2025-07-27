package cn.wanyj.mcp.server.type.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author 永
 */
@Getter
@ConfigurationProperties(prefix = "juejin.api")
@Component
public class JueJinApiProperties {

    private String cookie;

    private String categories;
    private String authorization;

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}

