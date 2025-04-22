package cn.bms.common.exception.user;

public class UserDeleteTokenException extends UserException{
    public UserDeleteTokenException(){
        super(4009,"token.delete","删除登录信息失败");
    }
}
