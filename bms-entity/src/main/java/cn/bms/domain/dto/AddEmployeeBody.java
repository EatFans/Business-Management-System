package cn.bms.domain.dto;

/**
 * 添加员工数据实体对象
 *
 * @author Fan
 */
public class AddEmployeeBody {
    private String nickName;
    private String email;
    private String phoneNumber;
    private String sex;
    private String avatar;
    private Long deptId;
    private Long posId;

    public String getNickName() {return nickName;}
    public String getEmail() {return email;}
    public String getPhoneNumber() {return phoneNumber;}
    public String getSex() {return sex; }
    public String getAvatar() {return avatar;}

    public Long getDeptId() {
        return deptId;
    }

    public Long getPosId() {
        return posId;
    }
}
