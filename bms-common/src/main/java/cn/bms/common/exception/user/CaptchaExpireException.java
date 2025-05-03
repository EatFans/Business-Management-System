package cn.bms.common.exception.user;

/**
 * 图片验证码过期异常错误类
 *
 * @author Fan
 */
public class CaptchaExpireException extends UserException{
    public CaptchaExpireException(){
        super(4002,"captcha","图片验证码已经失效");
    }
}
