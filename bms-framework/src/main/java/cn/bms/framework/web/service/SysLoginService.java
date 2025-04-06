package cn.bms.framework.web.service;

import org.springframework.stereotype.Component;


@Component
public class SysLoginService {


    public SysLoginService(){

    }

    /**
     * 系统登录业务
     * @param username 用户名
     * @param password 用户密码
     * @param code 图片验证码
     * @param uuid 唯一标识符
     * @return  登录业务处理完毕，返回生成的token登录令牌
     */
    public String login(String username, String password, String code, String uuid){
        checkImageCode(username,code,uuid); // 检查图片验证码
        loginPreCheck(username,password);  // 登录前置条件检查

        // 登录验证


        // 生成token
        return " ";
    }

    private void checkImageCode(String username,String code,String uuid) {

    }

    private void loginPreCheck(String username, String password) {

    }
}
