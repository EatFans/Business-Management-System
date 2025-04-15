package cn.bms.web.controller.tool;

import cn.bms.domain.ApiResponse;
import cn.bms.domain.dto.TestBody;
import cn.bms.domain.entity.Employee;
import cn.bms.system.mapper.EmployeeMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
