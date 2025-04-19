package cn.bms.common.exception;

/**
 * 图片验证码过期异常错误类
 *
 * @author Fan
 */
public class CaptchaExpireException extends RuntimeException{
    public CaptchaExpireException(){
        super("图片验证码已经失效");
    }
}
