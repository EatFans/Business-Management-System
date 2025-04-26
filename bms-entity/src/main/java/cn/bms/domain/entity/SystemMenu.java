package cn.bms.domain.entity;

/**
 * 系统菜单数据对象类
 *
 * @author Fan
 */
public class SystemMenu extends BaseEntity{
    private Long menuId;
    private String text;
    private String icon;
    private String link;
    private int orderNumber;

    public void setMenuId(Long menuId){
        this.menuId = menuId;
    }

    public Long getMenuId(){
        return menuId;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public void setIcon(String icon){
        this.icon = icon;
    }

    public String getIcon(){
        return icon;
    }

    public void setLink(String link){
        this.link = link;
    }

    public String getLink(){
        return link;
    }

    public void setOrderNumber(int orderNumber){
        this.orderNumber = orderNumber;
    }

    public int getOrderNumber(){
        return orderNumber;
    }
}
