package cn.bms.domain.entity;

/**
 * 系统角色与菜单关联数据对象
 *
 * @author Fan
 */
public class SystemRoleMenu {
    private Long id;
    private Long roleId;
    private Long menuId;

    public void setId(Long id){
        this.id = id;
    }

    public Long getId(){
        return id;
    }

    public void setRoleId(Long roleId){
        this.roleId = roleId;
    }

    public Long getRoleId(){
        return roleId;
    }

    public void setMenuId(Long menuId){
        this.menuId = menuId;
    }

    public Long getMenuId(){
        return menuId;
    }
}
