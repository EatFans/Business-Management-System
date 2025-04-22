package cn.bms.web.controller.system;

import cn.bms.common.constant.Constants;
import cn.bms.domain.ApiResponse;
import cn.bms.domain.dto.LoginBody;
import cn.bms.domain.model.LoginUser;
import cn.bms.framework.web.service.SysLoginService;
import cn.bms.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统登录控制类
 *
 * @author Fan
 */
@RestController
public class SysLoginController {

    @Autowired
    private SysLoginService loginService;
    @Autowired
    private TokenService tokenService;

    /**
     * 登录接口
     *
     * @param loginBody 登录请求体数据
     * @return 返回封装好的响应结果
     */
    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginBody loginBody){
        ApiResponse response = ApiResponse.success();
        String token = loginService.login(loginBody.getUsername(),
                loginBody.getPassword(),
                loginBody.getCode(),
                loginBody.getUuid());
        response.put(Constants.TOKEN,token);
        return response;
    }

    /**
     * 验证token接口
     * @param request http请求
     * @return 结果
     */
    @GetMapping("/verityToken")
    public ApiResponse verityToken(HttpServletRequest request){
        ApiResponse response = ApiResponse.success();
        LoginUser loginUser = tokenService.getLoginUser(request);
        return loginUser != null ? response : ApiResponse.error();
    }

    @PostMapping("/logout")
    public ApiResponse logout(HttpServletRequest request){
        loginService.logout(request);

        return ApiResponse.success("退出登录成功!");
    }

}
