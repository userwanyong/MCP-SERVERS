package cn.wanyj.mcp.server;

import cn.wanyj.mcp.server.domain.service.JueJinArticleService;
import cn.wanyj.mcp.server.infrastructure.gateway.IJueJinService;
import cn.wanyj.mcp.server.type.properties.JueJinApiProperties;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author 永
 */
@SpringBootApplication
@Slf4j
public class McpServerJueJinApplication implements CommandLineRunner {

    @Resource
    private JueJinApiProperties properties;

    public static void main(String[] args) {
        SpringApplication.run(McpServerJueJinApplication.class, args);
    }

    /**
     * 配置JUEJIN API服务
     *
     * @return IJueJinService Bean
     */
    @Bean
    public IJueJinService jueJinService(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.juejin.cn/")
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit.create(IJueJinService.class);
    }

    @Bean
    public ToolCallbackProvider juejinTools(JueJinArticleService jueJinArticleService) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(jueJinArticleService)
                .build();
    }

    @Override
    public void run(String... args) {
        log.info("check properties ...");
        if (properties.getCookie() == null) {
            log.error("weixin properties key is null, please set it in application.yml");
        } else {
            log.info("weixin properties key cookie:{} categories:{} authorization:{}", properties.getCookie(),properties.getCategories(),properties.getAuthorization());
        }
    }
}
