package cn.bms.web.controller.tool;

import cn.bms.common.exception.user.CaptchaExpireException;
import cn.bms.domain.ApiResponse;
import cn.bms.domain.dto.TestBody;
import cn.bms.domain.entity.*;
import cn.bms.system.mapper.EmployeeMapper;
import cn.bms.system.mapper.RoleMapper;
import cn.bms.system.mapper.SystemMenuMapper;
import cn.bms.system.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Test测试控制器
 *
 * @author Fan
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private SystemMenuMapper systemMenuMapper;

    public TestController(){

    }

    @GetMapping("/hello")
    public String test(){
        return "Hello World";
    }


    @GetMapping("/1234")
    public ApiResponse test2(@RequestParam String username, @RequestParam String password){
        ApiResponse response = ApiResponse.success();

        response.put("username",username);
        response.put("password",password);


        return response;

    }

    @PostMapping("/addEmployee")
    public ApiResponse addEmployee(){

        ApiResponse response = ApiResponse.success();
        Employee employee = new Employee();
        employee.setJobNumber("20240203627");
        employee.setEmail("123456@test.com");
        employee.setPhoneNumber("12334566");
        employee.setPassword("123456");

        int i = employeeMapper.insertEmployee(employee);
        if (i == 0)
            return ApiResponse.error("添加员工数据失败");
        return response;
    }

    @PostMapping("/updateEmployee")
    public ApiResponse updateEmployee(@RequestBody TestBody body){
        ApiResponse response = ApiResponse.success();

        Employee updatedEmployee = new Employee();
        updatedEmployee.setJobNumber(body.getJobNumber());
        updatedEmployee.setDeptId(body.getDeptId());
        updatedEmployee.setPosId(body.getPosId());
        updatedEmployee.setNickName(body.getNickName());
        updatedEmployee.setEmail(body.getEmail());
        updatedEmployee.setPhoneNumber(body.getPhoneNumber());
        updatedEmployee.setSex(body.getSex());
        updatedEmployee.setStatus(body.getStatus());
        updatedEmployee.setPassword(body.getPassword());

        int i = employeeMapper.updateEmployee(updatedEmployee);
        if (i == 0)
            return ApiResponse.error("更新失败");
        return response;
    }

    @GetMapping("/testException")
    public ApiResponse test1(){
        ApiResponse response = ApiResponse.success();
        testException();
        return response;
    }

    private void testException(){
        throw new CaptchaExpireException();
    }

    @PostMapping("/addPermission")
    public ApiResponse addPermission(@RequestBody Permission permission){
        ApiResponse response = ApiResponse.success("成功添加权限");

        permission.setCreateTime(new Date(System.currentTimeMillis()));
        permission.setCreateBy("System");

        boolean b = permissionService.addPermission(permission);
        return b ? response : ApiResponse.error("添加权限失败");
    }

    @PostMapping("/updatePermission")
    public ApiResponse updatePermission(@RequestBody Permission permission){
        ApiResponse response = ApiResponse.success("成功更新权限");

        boolean b = permissionService.updatePermission(permission);
        return b ? response : ApiResponse.error("更新权限失败");
    }

    @PostMapping("/addRole")
    public ApiResponse addRole(@RequestBody Role role){
        ApiResponse response = ApiResponse.success("成功添加角色");

        role.setCreateTime(new Date(System.currentTimeMillis()));
        role.setCreateBy("System");

        int i = roleMapper.insertRole(role);
        return i > 0 ? response : ApiResponse.error("添加角色失败");
    }

    @PostMapping("/updateRole")
    public ApiResponse updateRole(@RequestBody Role role){
        ApiResponse response = ApiResponse.success("成功修改");

        if (role.getRoleId() <= 0)
            return ApiResponse.error("修改失败！");

        int i = roleMapper.updateRole(role);


        return i > 0 ? response : ApiResponse.error("修改失败");
    }

    @PostMapping("/addSystemMenu")
    public ApiResponse addSystemMenu(@RequestBody SystemMenu systemMenu){
        ApiResponse response = ApiResponse.success("添加菜单成功");

        systemMenu.setCreateTime(new Date(System.currentTimeMillis()));
        systemMenu.setCreateBy("System");

        int i = systemMenuMapper.insertSystemMenu(systemMenu);
        return i > 0 ? response : ApiResponse.error("添加菜单选项错误");
    }

    @PostMapping("/updateSystemMenu")
    public ApiResponse updateSystemMenu(@RequestBody SystemMenu systemMenu){
        ApiResponse response = ApiResponse.success("成功修改");

        if (systemMenu.getMenuId() <= 0)
            return ApiResponse.error("修改失败");
        int i = systemMenuMapper.updateSystemMenu(systemMenu);
        return i > 0 ? response : ApiResponse.error("修改失败");
    }

    @PostMapping("/addEmployeeRole")
    public ApiResponse addEmployeeRole(@RequestBody EmployeeRole body){
        ApiResponse response = ApiResponse.success("添加成功");
        int i = employeeMapper.addEmployeeRole(body);
        if (i <= 0)
            return ApiResponse.error("添加失败");
        return response;
    }
}
