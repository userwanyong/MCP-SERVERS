package cn.wanyj.mcp.server.infrastructure.config;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * HTTP客户端配置类
 * @author 永
 */
@Configuration
public class HttpClientConfig {

    /**
     * 配置OkHttpClient
     *
     * @return OkHttpClient实例
     */
    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(new ContentTypeInterceptor())
                .build();
    }

    /**
     * Content-Type拦截器
     * 为所有请求添加Content-Type: application/json头
     */
    static class ContentTypeInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();

            // 为请求添加Content-Type头
            Request newRequest = originalRequest.newBuilder()
                    .header("Content-Type", "application/json")
                    .build();

            return chain.proceed(newRequest);
        }
    }
}