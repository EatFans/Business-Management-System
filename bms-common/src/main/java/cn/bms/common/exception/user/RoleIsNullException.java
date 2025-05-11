package cn.bms.common.exception.user;

/**
 * 员工角色为空错误异常
 */
public class RoleIsNullException extends UserException{
    public RoleIsNullException(){
        super(4010,"role","员工角色为空错误异常");
    }
}
