package cn.bms.common.core.domain.model;

import org.springframework.security.core.userdetails.UserDetails;


/**
 * 登录用户身份权限
 * 
 */
//public class LoginUser implements UserDetails
public class LoginUser {
    private String username;
    private String token;

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public void setToken(String token){
        this.token = token;
    }

    public String getToken(){
        return token;
    }
}
