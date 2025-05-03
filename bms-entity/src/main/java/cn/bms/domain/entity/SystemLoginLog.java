package cn.bms.domain.entity;

import java.util.Date;

/**
 * 系统登录日志数据实体对象
 *
 * @author Fan
 */
public class SystemLoginLog {
    private Long id;
    private String jobNumber;
    private String ipAddress;
    private String agent;
    private String os;
    private String browser;
    private Date loginTime;
    private boolean status; // 登录状态 1成功， 0失败
    private String msg; // 登录消息
    private Date logTime;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setJobNumber(String jobNumber){
        this.jobNumber = jobNumber;
    }

    public String getJobNumber(){
        return jobNumber;
    }

    public void setIpAddress(String ipAddress){
        this.ipAddress = ipAddress;
    }

    public String getIpAddress(){
        return ipAddress;
    }

    public void setAgent(String agent){
        this.agent = agent;
    }

    public String getAgent(){
        return agent;
    }

    public void setOs(String os){
        this.os = os;
    }

    public String getOs(){
        return os;
    }

    public void setBrowser(String browser){
        this.browser = browser;
    }

    public String getBrowser(){
        return browser;
    }

    public void setLoginTime(Date loginTime){
        this.loginTime = loginTime;
    }

    public Date getLoginTime(){
        return loginTime;
    }

    public void setStatus(boolean flag){
        this.status = flag;
    }

    public boolean isLoginSuccess(){
        return status;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

    public String getMsg(){
        return msg;
    }

    public void setLogTime(Date logTime){
        this.logTime = logTime;
    }

    public Date getLogTime(){
        return logTime;
    }
}
