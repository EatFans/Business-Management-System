package cn.bms.common.exception.user;

/**
 * 图片验证码错误异常类
 *
 * @author Fan
 */
public class CaptchaException extends UserException{
    public CaptchaException(){
        super(4001,"captcha","图片验证码错误");
    }
}
