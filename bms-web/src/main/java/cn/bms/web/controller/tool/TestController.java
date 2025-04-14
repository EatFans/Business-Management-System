package cn.bms.web.controller.tool;

import cn.bms.domain.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Test测试控制器
 *
 * @author Fan
 */
@RestController
@RequestMapping("/test")
public class TestController {
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
}
