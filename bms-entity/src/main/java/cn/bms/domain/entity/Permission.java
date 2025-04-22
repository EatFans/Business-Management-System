package cn.bms.domain.entity;

/**
 * 权限数据表数据实体对象
 *
 * @author Fan
 */
public class Permission extends BaseEntity {
    private Long permissionId;

    private String code;

    private String name;

    private boolean status;


    public void setPermissionId(Long permissionId){
        this.permissionId = permissionId;
    }

    public Long getPermissionId(){
        return permissionId;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setStatus(boolean flag){
        this.status = flag;
    }

    public boolean isEnable(){
        return status;
    }
}
