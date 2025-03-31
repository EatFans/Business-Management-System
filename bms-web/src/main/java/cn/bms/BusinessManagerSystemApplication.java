package cn.bms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * 启动程序
 * @author Fan
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class }) // 临时禁用security
public class BusinessManagerSystemApplication {
    public static void main(String[] args){
        SpringApplication.run(BusinessManagerSystemApplication.class,args);

    }
}
