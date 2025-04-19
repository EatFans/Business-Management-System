package cn.bms.framework.web.service;

import cn.bms.common.constant.CacheConstants;
import cn.bms.common.constant.UserConstants;
import cn.bms.common.core.redis.RedisCache;
import cn.bms.common.exception.service.ServiceException;
import cn.bms.common.exception.user.*;
import cn.bms.common.utils.StringUtils;
import cn.bms.domain.model.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class SysLoginService {
    @Autowired
    private RedisCache redisCache;

    @Autowired
    private TokenService tokenService;

    @Resource
    private AuthenticationManager authenticationManager;


    /**
     * 系统登录业务
     * @param username 用户名
     * @param password 用户密码
     * @param code 图片验证码
     * @param uuid 唯一标识符
     * @return  登录业务处理完毕，返回生成的token登录令牌
     */
    public String login(String username, String password, String code, String uuid){
        checkImageCode(username,code,uuid); // 检查图片验证码
        loginPreCheck(username,password);  // 登录前置条件检查

        Authentication authentication = null;
        // 登录验证
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(username, password);
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e){
            if (e instanceof BadCredentialsException){
                throw new UserAccountPasswordNotMatchException();
            }else {
                throw new ServiceException(4005,"null",e.getMessage());
            }
        }
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        recordLoginInfo(loginUser.getEmpId());
        // 生成token
        return tokenService.createLoginToken(loginUser);
    }

    /**
     * 记录登录信息
     * @param empId 员工id
     */
    private void recordLoginInfo(Long empId){
        // TODO: 更新员工用户数据表
    }

    /**
     * 检查图片验证码
     * @param username 用户名
     * @param code 验证码
     * @param uuid 验证码唯一标识符
     */
    private void checkImageCode(String username,String code,String uuid) {
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + StringUtils.nvl(uuid, "");
        String captcha = redisCache.getCacheObject(verifyKey);
        if (captcha == null){
            throw new CaptchaExpireException();
        }
        if (!code.equalsIgnoreCase(captcha)){
            throw new CaptchaException();
        }
        redisCache.deleteObject(verifyKey);
    }

    /**
     * 登录前做准备检查
     * @param username 用户名
     * @param password 用户密码
     */
    private void loginPreCheck(String username, String password) {
        // 用户名或者密码为空
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
            throw new UserNameOrPasswordNullException();
        }
        // 检查密码是否在指定范围
        if (password.length() < UserConstants.PASSWORD_MIN_LENGTH
                || password.length() > UserConstants.PASSWORD_MAX_LENGTH){
            throw new UserNotMatchException();
        }
        // 检查用户名是否在指定范围
        if (username.length() < UserConstants.USERNAME_MIN_LENGTH
                || username.length() > UserConstants.USERNAME_MAX_LENGTH){
            throw new UserNotMatchException();
        }

    }
}
