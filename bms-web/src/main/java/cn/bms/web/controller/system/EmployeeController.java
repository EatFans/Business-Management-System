package cn.bms.web.controller.system;

import cn.bms.common.utils.StringUtil;
import cn.bms.domain.ApiResponse;
import cn.bms.domain.entity.Employee;
import cn.bms.system.service.EmployeeService;
import io.swagger.annotations.Api;
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
        if (StringUtil.isNull(jobNumber))
            return ApiResponse.error("工号生成失败");
        employee.setJobNumber(jobNumber);
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

        boolean b = employeeService.addEmployee(employee);
        if (!b)
            return ApiResponse.error("添加 " + employee.getNickName() +" 员工失败");
        return response.put("data",employee);
    }

}
