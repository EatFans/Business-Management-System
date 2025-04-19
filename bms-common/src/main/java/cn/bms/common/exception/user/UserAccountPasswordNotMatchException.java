package cn.bms.common.exception.user;

/**
 * 用户账号密码不匹配异常错误
 *
 * @author Fan
 */
public class UserAccountPasswordNotMatchException extends UserException{
    public UserAccountPasswordNotMatchException(){
        super(4008,"auth","账号密码错误");
    }
}
