package cn.bms.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * SpringBoot Security配置类
 *
 * @author Fan
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .antMatchers("/login").permitAll() //放行登录接口
                        .antMatchers("/captchaCode").permitAll()  // 获取验证码接口
                        // 其他请求都需要认证
                        .anyRequest().authenticated()
                )
                // 关闭 CSRF（前后端分离项目通常需要关闭）
                .csrf(csrf -> csrf.disable());

        return http.build();
    }
}


