package cn.bms.web.controller.system;

import cn.bms.domain.ApiResponse;
import cn.bms.domain.entity.Role;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public ApiResponse addRole(@RequestBody Role role, HttpServletRequest request){
        ApiResponse response = ApiResponse.success();

        return response;
    }

    @PutMapping("/update")
    public ApiResponse updateRole(@RequestBody Role role,HttpServletRequest request){
        ApiResponse response = ApiResponse.success();

        return response;
    }

    @GetMapping("/get")
    public ApiResponse getRole(@RequestParam("id") Long id){
        ApiResponse response = ApiResponse.success();

        return response;
    }

    @GetMapping("/get/list")
    public ApiResponse getRoleList(){
        ApiResponse response = ApiResponse.success();

        return response;
    }

    @DeleteMapping("/delete")
    public ApiResponse deleteRole(@RequestParam("id")Long id){
        ApiResponse response = ApiResponse.success();

        return response;
    }
}
