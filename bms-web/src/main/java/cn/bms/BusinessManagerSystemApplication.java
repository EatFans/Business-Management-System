package cn.bms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * 启动程序
 * @author Fan
 */
@SpringBootApplication // 临时禁用security
@MapperScan("cn.bms.system.mapper")
public class BusinessManagerSystemApplication {
    public static void main(String[] args){
        SpringApplication.run(BusinessManagerSystemApplication.class,args);

    }
}
