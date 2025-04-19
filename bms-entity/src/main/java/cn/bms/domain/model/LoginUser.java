package cn.bms.domain.model;

import cn.bms.domain.entity.Employee;
import com.alibaba.fastjson2.annotation.JSONField;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


/**
 * 登录用户身份权限
 * 
 */
public class LoginUser implements UserDetails {
    private Long empId;
    @JSONField(name = "employee")
    private Employee employee;
    private String loginUserKey;  // 标识符存在redis中uuid
    private Long loginTime;
    private Long expireTime;

    public LoginUser(Long empId,Employee employee){
        this.empId = empId;
        this.employee = employee;
    }

    public void setEmpId(Long empId){
        this.empId = empId;
    }
    public Long getEmpId(){
        return empId;
    }

    public void setLoginTime(Long loginTime){
        this.loginTime = loginTime;
    }

    public Long getLoginTime(){
        return loginTime;
    }

    public void setExpireTime(Long expireTime){
        this.expireTime = expireTime;
    }

    public Long getExpireTime(){
        return expireTime;
    }

    public void setLoginUserKey(String loginUserKey){
        this.loginUserKey = loginUserKey;
    }

    public String getLoginUserKey(){
        return loginUserKey;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JSONField(serialize = false)
    @Override
    public String getPassword() {
        return employee.getPassword();
    }

    @Override
    public String getUsername() {
        return employee.getJobNumber();
    }


    /**************************************/
    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JSONField(serialize = false)
    @Override
    public boolean isEnabled() {
        return true;
    }

    public Employee getEmployee() {
        return employee;
    }
}
