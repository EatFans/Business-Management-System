package cn.bms.web.controller.system;

import cn.bms.common.constant.Constants;
import cn.bms.common.core.domain.ApiResponse;
import cn.bms.common.core.domain.model.LoginBody;
import cn.bms.framework.web.service.SysLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统登录控制类
 *
 * @author Fan
 */
@RestController
public class SysLoginController {

    private final SysLoginService loginService;

    @Autowired
    public SysLoginController(SysLoginService loginService){
        this.loginService = loginService;

    }

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
}
