package cn.bms.web.controller.employee;

import cn.bms.common.utils.SecurityUtils;
import cn.bms.common.utils.StringUtils;
import cn.bms.domain.ApiResponse;
import cn.bms.domain.entity.Employee;
import cn.bms.system.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统用户控制器类
 *
 * @author Fan
 */
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public ApiResponse addEmployee(@RequestBody Employee employee){
        ApiResponse response = ApiResponse.success();
        String jobNumber = employeeService.generateJobNumber();
        if (StringUtils.isNull(jobNumber))
            return ApiResponse.error("工号生成失败");
        employee.setJobNumber(jobNumber);
        employee.setPassword(SecurityUtils.encryptPassword(employee.getPassword()));
        if (!employeeService.checkJobNumberUnique(employee)){
            return ApiResponse.error("工号不唯一，无法创建员工");
        }
        // 员工邮箱是不是唯一的
        if (!employeeService.checkEmailUnique(employee)){
            return ApiResponse.error("邮箱不唯一，无法创建员工");
        }
        // 员工手机号码是不是唯一的
        if (!employeeService.checkPhoneNumberUnique(employee)){
            return ApiResponse.error("手机号不唯一，无法创建员工");
        }

        // 添加员工数据
        boolean flag1 = employeeService.addEmployee(employee);

        // 添加员工默认的角色
        boolean flag2 = employeeService.assignDefaultRole(employee.getEmpId());


        if (!flag1)
            return ApiResponse.error("添加 " + employee.getNickName() +" 员工失败");
        if (!flag2)
            return ApiResponse.error("分配默认角色失败");
        return response.put("data",employee);
    }

}
