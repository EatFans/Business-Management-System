package cn.bms.common.utils;

/**
 * 字符串工具类
 *
 * @author Fan
 */
public class StringUtil {
    private static final String NULLSTR = "";

    /**
     * 检查字符串是否是一个空字符串
     * @param str 字符串
     * @return 如果是空就返回true，否则返回false
     */
    public static boolean isEmpty(String str){
        return isNull(str) || NULLSTR.equals(str.trim());
    }

    /**
     * 检查对象是否位空
     * @param object 对象
     * @return 如果是空就返回true，否则就返回false
     */
    public static boolean isNull(Object object){
        return object == null;
    }
}
