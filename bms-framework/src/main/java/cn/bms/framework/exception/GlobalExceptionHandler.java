package cn.bms.framework.exception;

import cn.bms.common.exception.user.UserException;
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
     * 用户部分异常错误
     */
    @ExceptionHandler(UserException.class)
    public ApiResponse handler(UserException e){
        log.warn(e.getMessage() + " -- "+ e.getModule());
        ApiResponse error = ApiResponse.error(e.getMessage());
        error.put("status",e.getCode());
        error.put("module",e.getModule());
        return error;
    }


}
