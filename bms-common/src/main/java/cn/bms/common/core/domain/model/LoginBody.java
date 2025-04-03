package cn.bms.common.core.domain.model;


public class LoginBody {
    private String username; // 用户名
    private String password;  // 密码
    private String code;   // 验证码
    private String uuid;   // 唯一标识符

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }

    public void setUuid(String uuid){
        this.uuid = uuid;
    }

    public String getUuid(){
        return uuid;
    }
}
