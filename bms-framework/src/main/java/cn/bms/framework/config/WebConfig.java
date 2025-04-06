package cn.bms.framework.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Value("${web.allowedOrigins}")
    private String allowedOrigins;

    @Bean
    public WebMvcConfigurer corsFilter() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry
                         .addMapping("/**")
                        .allowedOrigins(allowedOrigins)    // 指定的前端服务器ip
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  //允许的 HTTP 方法：
                        .allowedHeaders("*")
                        .allowCredentials(true); //允许前端携带 Cookie 或 Token
            }
        };
    }
}
