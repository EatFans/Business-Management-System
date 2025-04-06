package cn.bms.common.utils.uuid;


import cn.bms.common.utils.uuid.UUID;

/**
 * 随机uuid工具
 *
 * @author Fan
 */
public class UuidUtil {
    /**
     * 获取UUID
     * @return 返回随机UUID
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取简化UUID
     * @return 随机UUID
     */
    public static String easyUUID() {
        return UUID.randomUUID().toString(true);
    }

    /**
     * 获取随机UUID，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 随机UUID
     */
    public static String fastUUID() {
        return UUID.fastUUID().toString();
    }

    /**
     * 简化的UUID，去掉了横线，使用性能更好的ThreadLocalRandom生成UUID
     *
     * @return 简化的UUID，去掉了横线
     */
    public static String fastEasyUUID() {
        return UUID.fastUUID().toString(true);
    }
}
