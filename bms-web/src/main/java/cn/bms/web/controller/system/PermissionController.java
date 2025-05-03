package cn.bms.web.controller.system;

import cn.bms.common.utils.StringUtils;
import cn.bms.domain.entity.Permission;
import cn.bms.domain.ApiResponse;
import cn.bms.system.service.PermissionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 权限控制类
 *
 * @author Fan
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 添加权限接口方法
     * @param permission 权限信息
     * @return 结果
     */
    @PostMapping("/add")
    public ApiResponse addPermission(@RequestBody Permission permission){
        ApiResponse response = ApiResponse.success();

        if (!StringUtils.isNotEmpty(permission.getCode()))
            return ApiResponse.error("权限code不能为空");
        // 检查是否已经存在code
        if (!permissionService.checkPermissionUnique(permission.getCode())){
            return ApiResponse.error("权限不唯一，已经存在该权限");
        }
        // 默认设置权限就是启用的
        permission.setStatus(true);

        // 设置创建时间
        permission.setCreateTime(new Date(System.currentTimeMillis()));

        // TODO 设置创建者 （创建者填入角色）



        return response;
    }
}
