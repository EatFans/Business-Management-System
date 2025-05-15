package cn.bms.web.controller.system;

import cn.bms.common.utils.StringUtils;
import cn.bms.domain.ApiResponse;
import cn.bms.domain.entity.Role;
import cn.bms.domain.model.LoginUser;
import cn.bms.framework.web.service.TokenService;
import cn.bms.system.service.EmployeeService;
import cn.bms.system.service.RoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 系统角色控制类
 *
 * @author Fan
 */
@RestController
@RequestMapping("/system/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private EmployeeService employeeService;

    /**
     * 添加角色接口
     * @param role 角色
     * @return 响应结果
     */
    @PostMapping("/add")
    public ApiResponse addRole(@RequestBody Role role, HttpServletRequest request){
        ApiResponse response = ApiResponse.success();

        // 检查角色是否已经添加
        if (!StringUtils.isNotEmpty(role.getRoleKey()))
            return ApiResponse.error("角色key不能为空");
        if (!StringUtils.isNotEmpty(role.getRoleName()))
            return ApiResponse.error("角色name不能为空");

        if (!roleService.checkRoleUnique(role.getRoleName(),role.getRoleKey()))
            return ApiResponse.error("角色已经存在");

        // 默认启用
        role.setStatus(true);

        // 设置创建时间
        role.setCreateTime(new Date(System.currentTimeMillis()));

        // 设置创建者
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (loginUser == null)
            return ApiResponse.error(403,"登录数据不存在");
        Role serviceRole = employeeService.getRole(loginUser.getEmpId());
        role.setCreateBy(serviceRole.getRoleKey());

        boolean b = roleService.addRole(role);

        return b ? response : ApiResponse.error("添加员工失败");
    }

    /**
     * 更新角色接口方法
     * @param role 角色
     * @param request 请求
     * @return 响应
     */
    @PutMapping("/update")
    public ApiResponse updateRole(@RequestBody Role role,HttpServletRequest request){
        ApiResponse response = ApiResponse.success();

        // 检查传输过来的数据是否存在roleId
        Long roleId = role.getRoleId();
        if (roleId == null || roleId == 0)
            return ApiResponse.error("未知角色");

        // 设置更新事件
        role.setUpdateTime(new Date(System.currentTimeMillis()));

        // 设置更新者
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (loginUser == null)
            return ApiResponse.error(403,"登录数据不存在");
        Role serviceRole = employeeService.getRole(loginUser.getEmpId());
        role.setUpdateBy(serviceRole.getRoleKey());

        boolean b = roleService.updateRole(role);

        return b ? response : ApiResponse.error("更新失败");
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
