package cn.bms.common.exception.user;

import cn.bms.common.exception.BaseException;

/**
 * 用户异常错误类
 *
 * @author Fan
 */
public class UserException extends BaseException {
    public UserException(int code,String module, String message){
        super(code,"user."+module,message);
    }
}
