package cn.bms.common.exception;

/**
 * 用户名或者密码为空错误异常
 *
 * @author Fan
 */
public class UserNameOrPasswordNullException extends RuntimeException{
    public UserNameOrPasswordNullException(){
        super("用户名和密码不能为空");
    }
}
