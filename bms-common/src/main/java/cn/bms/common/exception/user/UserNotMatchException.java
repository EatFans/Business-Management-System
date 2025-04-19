package cn.bms.common.exception.user;


/**
 * 不符合规范错误异常
 */
public class UserNotMatchException extends UserException{
    public UserNotMatchException(){
        super(4004,"account","密码不符合规范");
    }
}
