package cn.wanyj.mcp.server.infrastructure.gateway.dto;


import lombok.*;

import java.io.Serial;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 永
 * 模版消息
 */
@Data
public class WeixinTemplateMsgRequestDTO {
    private String touser;
    private String template_id;
    private String url;
    private Map<String, Map<String, String>> data;

    public WeixinTemplateMsgRequestDTO(String touser, String template_id) {
        this.touser = touser;
        this.template_id = template_id;
    }

    public void put(TemplateKey key, String value) {
        data.put(key.getCode(), new HashMap<>() {
            @Serial
            private static final long serialVersionUID = 7092338402387318563L;

            {
                put("value", value);
            }
        });
    }

    public static void put(Map<String, Map<String, String>> data, TemplateKey key, String value) {
        data.put(key.getCode(), new HashMap<>() {
            @Serial
            private static final long serialVersionUID = 7092338402387318563L;

            {
                put("value", value);
            }
        });
    }


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public enum TemplateKey {
        PLATFORM("platform","平台"),
        SUBJECT("subject","主题"),
        DESCRIPTION("description","简述");

        private String code;
        private String desc;

    }


}
