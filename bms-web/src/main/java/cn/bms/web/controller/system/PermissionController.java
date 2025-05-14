package cn.bms.web.controller.system;

import cn.bms.common.utils.StringUtils;
import cn.bms.domain.entity.Permission;
import cn.bms.domain.ApiResponse;
import cn.bms.domain.entity.Role;
import cn.bms.domain.model.LoginUser;
import cn.bms.framework.web.service.TokenService;
import cn.bms.system.service.EmployeeService;
import cn.bms.system.service.PermissionService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 权限控制类
 *
 * @author Fan
 */
@RestController
@RequestMapping("/system/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmployeeService employeeService;

    /**
     * 添加权限接口方法
     * @param permission 权限信息
     * @return 结果
     */
    @PostMapping("/add")
    public ApiResponse addPermission(@RequestBody Permission permission, HttpServletRequest request){
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

        LoginUser loginUser = tokenService.getLoginUser(request);
        if (loginUser == null)
            return ApiResponse.error(403,"登录数据不存在");

        Role role = employeeService.getRole(loginUser.getEmpId());
        permission.setCreateBy(role.getRoleKey());

        boolean b = permissionService.addPermission(permission);
        return b ? response : ApiResponse.error("添加失败");
    }

    /**
     * 更新权限接口方法
     * @param permission 更新的权限
     * @param request 请求
     * @return 结果
     */
    @PutMapping("/update")
    public ApiResponse updatePermission(@RequestBody Permission permission, HttpServletRequest request){
        ApiResponse response = ApiResponse.success();

        // 检查传输过来的数据中是否存在permissionId
        Long permissionId = permission.getPermissionId();
        if (permissionId == null || permissionId == 0)
            return ApiResponse.error("未知权限");

        // 设置更新时间
        permission.setUpdateTime(new Date(System.currentTimeMillis()));

        // 设置更新者
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (loginUser == null)
            return ApiResponse.error(403,"登录数据不存在");
        Role role = employeeService.getRole(loginUser.getEmpId());
        permission.setUpdateBy(role.getRoleKey());

        boolean b = permissionService.updatePermission(permission);
        return b ? response : ApiResponse.error("更新失败");
    }

    /**
     * 获取权限
     * @param id 请求的id
     * @return 结果
     */
    @GetMapping("/get")
    public ApiResponse getPermission(@RequestParam("id") Long id){
        ApiResponse response = ApiResponse.success();
        if (id == null || id == 0)
            return ApiResponse.error("id不合法");

        Permission permission = permissionService.selectPermissionById(id);
        response.put("data",permission);
        return response;
    }

    /**
     * 获取全部权限
     * @return 全部权限
     */
    @GetMapping("/get/list")
    public ApiResponse getPermissionList(){
        ApiResponse response = ApiResponse.success();
        List<Permission> permissions = permissionService.selectPermission();
        response.put("data",permissions);
        return response;
    }

    /**
     * 删除权限接口
     * @param id 权限id
     * @param request 请求
     * @return 响应
     */
    @DeleteMapping("/delete")
    public ApiResponse deletePermission(@RequestParam("id") Long id,HttpServletRequest request){
        ApiResponse response = ApiResponse.success();
        boolean b = permissionService.deletePermission(id);
        return b ? response : ApiResponse.error("删除失败");
    }
}
