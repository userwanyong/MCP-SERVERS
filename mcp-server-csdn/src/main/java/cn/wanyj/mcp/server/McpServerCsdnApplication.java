package cn.wanyj.mcp.server;

import cn.wanyj.mcp.server.domain.service.CSDNArticleService;
import cn.wanyj.mcp.server.infrastructure.gateway.ICSDNService;
import cn.wanyj.mcp.server.type.properties.CSDNApiProperties;
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
public class McpServerCsdnApplication implements CommandLineRunner {

    @Resource
    private CSDNApiProperties properties;

    public static void main(String[] args) {
        SpringApplication.run(McpServerCsdnApplication.class, args);
    }
    
    /**
     * 配置CSDN API服务
     * 
     * @return ICSDNService Bean
     */
    @Bean
    public ICSDNService csdnService(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bizapi.csdn.net/")
                .client(okHttpClient)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        
        return retrofit.create(ICSDNService.class);
    }

    @Bean
    public ToolCallbackProvider csdnTools(CSDNArticleService csdnArticleService) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(csdnArticleService)
                .build();
    }

    @Override
    public void run(String... args) {
        log.info("check properties ...");
        if (properties.getCookie() == null) {
            log.error("csdn properties key is null, please set it in application.yml");
        } else {
            log.info("csdn properties key cookie:{} categories:{}", properties.getCookie(),properties.getCategories());
        }
    }
}
