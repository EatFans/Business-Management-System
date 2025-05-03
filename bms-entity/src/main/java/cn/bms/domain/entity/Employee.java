package cn.bms.domain.entity;


import java.util.Date;

/**
 * 员工数据实体类
 *
 * @author Fan
 */
public class Employee extends BaseEntity {
    private Long empId; // id
    private Long deptId;    // 所属部门id
    private Long posId;     // 所属岗位id
    private String jobNumber;   // 工号（账号）
    private String nickName;  // 员工昵称
    private String email;   // 邮箱
    private String phoneNumber;  // 手机号
    private String sex;  // 性别
    private String avatar;  // 头像
    private String password;  // 密码
    private String status;   // 状态
    private boolean delFlag; // 删除标识
    private boolean firstLogin;  // 是否第一次登录
    private Date loginTime;     // 登录时间

    public void setEmpId(Long empId){
        this.empId = empId;
    }

    public Long getEmpId(){
        return empId;
    }

    public void setDeptId(Long deptId){
        this.deptId = deptId;
    }

    public Long getDeptId(){
        return deptId;
    }

    public void setPosId(Long posId){
        this.posId = posId;
    }

    public Long getPosId(){
        return posId;
    }

    public void setJobNumber(String jobNumber){
        this.jobNumber = jobNumber;
    }

    public String getJobNumber(){
        return jobNumber;
    }

    public void setNickName(String nickName){
        this.nickName = nickName;
    }

    public String getNickName(){
        return nickName;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNUmber(){
        return phoneNumber;
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

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public void setDelFlag(boolean delFlag){
        this.delFlag = delFlag;
    }

    public boolean isDelFlag(){
        return delFlag;
    }

    public void setLoginTime(Date loginTime){
        this.loginTime = loginTime;
    }

    public Date getLoginTime(){
        return loginTime;
    }

    public void setFirstLogin(boolean firstLogin){
        this.firstLogin = firstLogin;
    }

    public boolean isFirstLogin(){
        return firstLogin;
    }

    public boolean isSuperAdmin(){
        return empId != null && empId == 1L;
    }
}
