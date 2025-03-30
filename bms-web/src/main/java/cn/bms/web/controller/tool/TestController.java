package cn.bms.web.controller.tool;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
