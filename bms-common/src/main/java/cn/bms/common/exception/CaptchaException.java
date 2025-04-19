package cn.bms.common.exception;

/**
 * 图片验证码错误异常类
 *
 * @author Fan
 */
public class CaptchaException extends RuntimeException{
    public CaptchaException(){
        super("图片验证码错误");
    }
}
