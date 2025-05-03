package cn.bms.domain.entity;

/**
 * 角色数据实体对象类
 *
 * @author Fan
 */
public class Role extends BaseEntity{
    private Long roleId;
    private String roleName;
    private String roleKey;
    private boolean status;
    private boolean delFlag;

    public void setRoleId(Long roleId){
        this.roleId = roleId;
    }

    public Long getRoleId(){
        return  roleId;
    }

    public void setRoleName(String roleName){
        this.roleName = roleName;
    }

    public String getRoleName(){
        return roleName;
    }

    public void setRoleKey(String roleKey){
        this.roleKey = roleKey;
    }

    public String getRoleKey(){
        return roleKey;
    }

    public void setStatus(boolean status){
        this.status = status;
    }

    public boolean isEnable(){
        return status;
    }

    public void setDelFlag(boolean delFlag){
        this.delFlag = delFlag;
    }

    public boolean getDelFlag(){
        return delFlag;
    }
}
