package cn.bms.common.exception.service;

import cn.bms.common.exception.BaseException;

/**
 * 业务错误异常
 */
public class ServiceException extends BaseException {
    public ServiceException(int code, String module, String message){
        super(code, "service."+module,message);
    }
}
