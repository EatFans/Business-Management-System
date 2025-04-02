package cn.bms.common.core.domain.entity;


import java.util.Date;

/**
 * 实体通用基类
 *
 * @author Fan
 */
public class BaseEntity {
    private String createBy; // 创建者
    private Date createTime; // 创建时间
    private String updateBy; // 更新者
    private Date updateTime; // 更新时间
    private String remark;  // 备注

    public void setCreateBy(String createBy){
        this.createBy = createBy;
    }

    public String getCreateBy(){
        return createBy;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    public Date getCreateTime(){
        return createTime;
    }

    public void setUpdateBy(String updateBy){
        this.updateBy = updateBy;
    }

    public String getUpdateBy(){
        return updateBy;
    }

    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    public Date getUpdateTime(){
        return updateTime;
    }

    public void setRemark(String remark){
        this.remark = remark;
    }

    public String getRemark(){
        return remark;
    }
}
