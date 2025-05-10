package cn.bms.domain.entity;

public class EmployeeRole {
    private Long id;
    private Long empId;
    private Long roleId;
    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setEmpId(Long empId){
        this.empId = empId;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRoleId() {
        return roleId;
    }
}
