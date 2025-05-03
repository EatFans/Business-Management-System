package cn.bms.common.exception.user;

/**
 * 用户名或者密码为空错误异常
 *
 * @author Fan
 */
public class UserNameOrPasswordNullException extends UserException{
    public UserNameOrPasswordNullException(){
        super(4003,"account","用户名和密码不能为空");
    }
}
