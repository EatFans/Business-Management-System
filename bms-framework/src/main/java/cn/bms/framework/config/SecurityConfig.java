package cn.bms.framework.config;

import cn.bms.framework.security.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.CorsFilter;

/**
 * SpringBoot Security配置类
 *
 * @author Fan
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * token认证过滤器
     */
    @Autowired
    private JwtAuthenticationTokenFilter authenticationTokenFilter;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.logout().disable()
                .authorizeHttpRequests(auth -> auth
                        .antMatchers("/login").permitAll() //放行登录接口
                        .antMatchers("/captchaCode").permitAll()  // 获取验证码接口
//                        .antMatchers("/logout").permitAll()
                        // 其他请求都需要认证
                        .anyRequest().authenticated()
                )
                // 关闭 CSRF（前后端分离项目通常需要关闭
                .csrf(csrf -> csrf.disable())
                // 添加 JWT token验证过滤器
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}


