package cn.bms.web.controller.system;

import cn.bms.domain.ApiResponse;
import cn.bms.domain.entity.Role;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统角色控制类
 *
 * @author Fan
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {

    /**
     * 添加角色接口
     * @param role 角色
     * @return 响应结果
     */
    @PostMapping("/add")
    public ApiResponse addRole(@RequestBody Role role){
        ApiResponse response = ApiResponse.success();

        return response;
    }
}
