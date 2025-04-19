package cn.bms.common.exception;


/**
 * 基础异常
 *
 * @author Fan
 */
public class BaseException extends RuntimeException{
    private String module;
    private int code; // 错误码
    private String message;  // 错误消息

    public BaseException(int code,String module, String message){
        this.code = code;
        this.module = module;
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }

    public int getCode(){
        return code;
    }

    public String getModule(){
        return module;
    }
}
