package cn.bms.framework.security.filter;

import cn.bms.common.utils.SecurityUtils;
import cn.bms.common.utils.StringUtils;
import cn.bms.domain.model.LoginUser;
import cn.bms.framework.web.service.TokenService;
import cn.bms.framework.web.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
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
    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private TokenService tokenService;

    @Autowired
    public JwtAuthenticationTokenFilter(TokenService tokenService){
        this.tokenService = tokenService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        LoginUser loginUser = tokenService.getLoginUser(request);
        if (loginUser != null && StringUtils.isNull(SecurityUtils.getAuthentication())){
            // 验证token
            tokenService.verifyToken(loginUser);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    loginUser, null, loginUser.getAuthorities());
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // 设置SecurityContextHolder中的认证信息
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }
        // 放行
        filterChain.doFilter(request, response);
    }

}
