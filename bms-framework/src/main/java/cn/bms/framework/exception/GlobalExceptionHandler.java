package cn.bms.framework.exception;

import cn.bms.common.exception.CaptchaException;
import cn.bms.common.exception.CaptchaExpireException;
import cn.bms.common.exception.UserNameOrPasswordNullException;
import cn.bms.common.exception.UserPasswordNotMatchException;
import cn.bms.domain.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author Fan
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 图片验证码过期异常
     */
//    @ExceptionHandler(CaptchaExpireException.class)
//    public ApiResponse handlerCaptchaExpireException(CaptchaExpireException e){
//        log.warn(e.getMessage());
//        return ApiResponse.error(e.getMessage());
//    }

    @ExceptionHandler(Exception.class)
    public ApiResponse handler(Exception e){
        log.warn(e.getMessage());
        return ApiResponse.error(e.getMessage());
    }

    /**
     * 图片验证码错误异常
     */
//    @ExceptionHandler(CaptchaException.class)
//    public ApiResponse handleCaptchaException(CaptchaException e){
//        log.warn(e.getMessage());
//        return ApiResponse.error(e.getMessage());
//    }

    /**
     *  用户名或密码为空异常
     */
//    @ExceptionHandler(UserNameOrPasswordNullException.class)
//    public ApiResponse handlerUsernameOrPasswordNullException(UserNameOrPasswordNullException e){
//        log.warn(e.getMessage());
//        return ApiResponse.error(e.getMessage());
//    }

//    @ExceptionHandler(UserPasswordNotMatchException.class)

}
