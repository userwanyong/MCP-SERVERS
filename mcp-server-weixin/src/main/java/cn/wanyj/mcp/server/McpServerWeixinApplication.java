package cn.wanyj.mcp.server;

import cn.wanyj.mcp.server.domain.service.WeixinNoticeService;
import cn.wanyj.mcp.server.infrastructure.gateway.IWeixinService;
import cn.wanyj.mcp.server.type.properties.WeixinApiProperties;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author 永
 */
@SpringBootApplication
@Slf4j
public class McpServerWeixinApplication implements CommandLineRunner {

    @Resource
    private WeixinApiProperties properties;

    public static void main(String[] args) {
        SpringApplication.run(McpServerWeixinApplication.class, args);
    }

    @Bean
    public IWeixinService weixinApiService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.weixin.qq.com/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit.create(IWeixinService.class);
    }

    @Bean(name = "weixinAccessToken")
    public Cache<String, String> weixinAccessToken() {
        return CacheBuilder.newBuilder()
                .expireAfterWrite(2, TimeUnit.HOURS)
                .build();
    }

    @Bean
    public ToolCallbackProvider weixinTools(WeixinNoticeService weixinNoticeService) {
        return MethodToolCallbackProvider.builder().toolObjects(weixinNoticeService).build();
    }

    @Override
    public void run(String... args) {
        log.info("check properties ...");
        if (properties.getApp_id() == null || properties.getApp_secret() == null || properties.getTouser() == null || properties.getTemplate_id() == null || properties.getOriginal_id() == null) {
            log.error("weixin properties key is null, please set it in application.yml");
        } else {
            log.info("weixin properties key appid:{} appsecret:{} template_id:{} touser:{} original_id:{}", properties.getApp_id(),properties.getApp_secret(),properties.getTemplate_id(),properties.getTouser(),properties.getOriginal_id());
        }
    }


}
