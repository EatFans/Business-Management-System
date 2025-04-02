package cn.bms.common.core.domain.entity;

import java.util.Date;

/**
 * 系统用户数据对象类
 *
 * @author Fan
 */
public class SysUser  extends BaseEntity {
    private Long  userId;   // 用户id
    private Long  deptId;   // 用户部门id
    private Long posId;     // 用户岗位id
    private String userName;  // 用户登录名
    private String nickName;  // 用户昵称
    private String userType;  // 用户类型
    private String email;   // 用户邮箱
    private String phonenumber; // 用户手机号
    private String sex;     // 用户性别 0男 1女 2未知
    private String avatar;  // 用户头像
    private String password; // 用户密码
    private String salt;  // 盐
    private String status;  // 账号状态

    private String delFlag; // 删除标识符

    private Date loginTime; // 登录时间
    private Date pwdUpdateTime; // 密码更新时间

    public void setUserId(Long userId){
        this.userId = userId;
    }

    public Long getUserId(){
        return userId;
    }

    public void setDeptId(Long deptId){
        this.deptId = deptId;
    }

    public Long getDepId(){
        return deptId;
    }

    public void setPosId(Long posId){
        this.posId = posId;
    }

    public Long getPosId(){
        return posId;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserName(){
        return userName;
    }

    public void setNickName(String nickName){
        this.nickName = nickName;
    }

    public String getNickName(){
        return nickName;
    }

    public void setUserType(String userType){
        this.userType = userType;
    }

    public String getUserType(){
        return userType;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setPhonenumber(String phonenumber){
        this.phonenumber = phonenumber;
    }

    public String getPhonenumber(){
        return phonenumber;
    }

    public void setSex(String sex){
        this.sex = sex;
    }

    public String getSex(){
        return sex;
    }

    public void setAvatar(String avatar){
        this.avatar = avatar;
    }

    public String getAvatar(){
        return avatar;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setSalt(String salt){
        this.salt = salt;
    }

    public String getSalt(){
        return salt;
    }

    public void setStatus(String status){
        this.status =status;
    }

    public String getStatus(){
        return status;
    }

    public void setDelFlag(String delFlag){
        this.delFlag = delFlag;
    }

    public String getDelFlag(){
        return delFlag;
    }

    public void setLoginTime(Date loginTime){
        this.loginTime = loginTime;
    }

    public Date getLoginTime(){
        return loginTime;
    }

    public void setPwdUpdateTime(Date pwdUpdateTime){
        this.pwdUpdateTime = pwdUpdateTime;
    }

    public Date getPwdUpdateTime(){
        return  pwdUpdateTime;
    }

    public SysUser(){}

    public SysUser(Long userId){
        this.userId = userId;
    }
}
