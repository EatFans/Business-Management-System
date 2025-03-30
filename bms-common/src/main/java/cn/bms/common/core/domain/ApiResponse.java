package cn.bms.common.core.domain;

import cn.bms.common.constant.HttpStatus;

import java.util.HashMap;
import java.util.Objects;

/**
 * ApiResponse封装统一的API响应格式
 *
 * @author Fan
 */
public class ApiResponse extends HashMap<String,Object> {
    private static final long serialVersionUID = 1L;

    /** 状态码 */
    public static final String CODE_TAG = "code";

    /** 返回内容 */
    public static final String MSG_TAG = "msg";

    /** 数据对象 */
    public static final String DATA_TAG = "data";

    /**
     * 初始化，使其表示一个空消息
     */
    public ApiResponse(){}

    /**
     * 初始化一个新创建的 ApiResponse 对象
     * @param code 状态码
     * @param msg 返回内容
     */
    public ApiResponse(int code, String msg){
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
    }

    /**
     * 初始化一个新创建的 ApiResponse 对象
     * @param code 状态码
     * @param msg 返回消息
     * @param data 返回数据体
     */
    public ApiResponse(int code ,String msg, Object data){
        super.put(CODE_TAG,code);
        super.put(MSG_TAG,msg);
        if (data != null)
            super.put(DATA_TAG,data);
    }

    /**
     * 重写HashMap<>对象中的put()方法，用于去链式调用
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return 本类
     */
    @Override
    public ApiResponse put(String key,Object value){
        super.put(key,value);
        return this;
    }



    /**
     * 返回成功消息
     * @return 成功消息
     */
    public static ApiResponse success()
    {
        return ApiResponse.success("操作成功");
    }

    /**
     * 返回成功数据
     * @return 成功消息
     */
    public static ApiResponse success(Object data) {
        return ApiResponse.success("操作成功", data);
    }

    /**
     * 返回成功消息
     * @param msg 返回内容
     * @return 成功消息
     */
    public static ApiResponse success(String msg) {
        return ApiResponse.success(msg, null);
    }

    /**
     * 返回成功消息
     * @param msg 返回内容
     * @param data 数据对象
     * @return 成功消息
     */
    public static ApiResponse success(String msg, Object data) {
        return new ApiResponse(HttpStatus.SUCCESS, msg, data);
    }

    /**
     * 返回警告消息
     * @param msg 返回内容
     * @return 警告消息
     */
    public static ApiResponse warn(String msg) {
        return ApiResponse.warn(msg, null);
    }

    /**
     * 返回警告消息
     * @param msg 返回内容
     * @param data 数据对象
     * @return 警告消息
     */
    public static ApiResponse warn(String msg, Object data)
    {
        return new ApiResponse(HttpStatus.WARN, msg, data);
    }

    /**
     * 返回错误消息
     * @return 错误消息
     */
    public static ApiResponse error()
    {
        return ApiResponse.error("操作失败");
    }

    /**
     * 返回错误消息
     * @param msg 返回内容
     * @return 错误消息
     */
    public static ApiResponse error(String msg)
    {
        return ApiResponse.error(msg, null);
    }

    /**
     * 返回错误消息
     * @param msg 返回内容
     * @param data 数据对象
     * @return 错误消息
     */
    public static ApiResponse error(String msg, Object data)
    {
        return new ApiResponse(HttpStatus.ERROR, msg, data);
    }

    /**
     * 返回错误消息
     * @param code 状态码
     * @param msg 返回内容
     * @return 错误消息
     */
    public static ApiResponse error(int code, String msg)
    {
        return new ApiResponse(code, msg, null);
    }

    /**
     * 是否为成功消息
     * @return 结果
     */
    public boolean isSuccess()
    {
        return Objects.equals(HttpStatus.SUCCESS, this.get(CODE_TAG));
    }

    /**
     * 是否为警告消息
     * @return 结果
     */
    public boolean isWarn()
    {
        return Objects.equals(HttpStatus.WARN, this.get(CODE_TAG));
    }

    /**
     * 是否为错误消息
     * @return 结果
     */
    public boolean isError()
    {
        return Objects.equals(HttpStatus.ERROR, this.get(CODE_TAG));
    }
}
