package cn.bms.common.exception;


/**
 * 密码不符合规范错误异常
 */
public class UserPasswordNotMatchException extends RuntimeException{
    public UserPasswordNotMatchException(){
        super("密码不符合规范");
    }
}
