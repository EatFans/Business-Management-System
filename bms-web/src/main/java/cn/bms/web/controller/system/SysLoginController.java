package cn.bms.web.controller.system;

import cn.bms.common.core.domain.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统登录控制类
 *
 * @author Fan
 */

@RestController
public class SysLoginController {

    public SysLoginController(){

    }

    /**
     * 登录接口
     *
     * @return
     */
    @PostMapping("/login")
    public ApiResponse login(){
        ApiResponse response = ApiResponse.success();
        return response;
    }
}
