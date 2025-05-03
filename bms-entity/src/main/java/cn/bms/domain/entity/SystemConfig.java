package cn.bms.domain.entity;


/**
 * 系统设置配置文件数据对象实体类
 *
 * @author Fan
 */
public class SystemConfig extends BaseEntity{
    private int configId;
    private String configName;
    private String configKey;
    private String configValue;

    public void setConfigId(int configId){
        this.configId = configId;
    }

    public int getConfigId(){
        return configId;
    }

    public void setConfigName(String configName){
        this.configName = configName;
    }

    public String getConfigName(){
        return configName;
    }

    public void setConfigKey(String configKey){
        this.configKey = configKey;
    }

    public String getConfigKey() {
        return configKey;
    }

    public void setConfigValue(String configValue){
        this.configValue = configValue;
    }

    public String getConfigValue(){
        return configValue;
    }
}
