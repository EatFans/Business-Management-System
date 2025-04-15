package cn.bms.framework.security.filter;

import cn.bms.common.utils.StringUtil;
import cn.bms.domain.model.LoginUser;
import cn.bms.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token过滤器 验证token有效性
 *
 * @author Fan
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    @Autowired
    public JwtAuthenticationTokenFilter(TokenService tokenService){
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // TODO :验证token，再请求到控制器层之前，来验证token，token验证成功了就

        String token = request.getHeader("Authorization");

        if (token != null) {
            if ( token.startsWith("Bearer "))
                token = token.substring(7); // 去掉 "Bearer "
        }
        // TODO: 测试: 如果头中有token为123456，就放行该请求
        if (token != null) {
            if (token.equalsIgnoreCase("123456"))
                filterChain.doFilter(request, response);
        }
        // 从请求中获取token
//        LoginUser loginUser = tokenService.getLoginUser(request);

        // 放行
        filterChain.doFilter(request, response);
    }

}
