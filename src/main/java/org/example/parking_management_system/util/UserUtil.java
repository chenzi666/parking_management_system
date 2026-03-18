package org.example.parking_management_system.util;

/**
 * 用户相关的工具类
 */
public class UserUtil {
    
    /**
     * 从ThreadLocal中获取当前用户的ID
     * @return 当前用户ID
     */
    public static Long getCurrentUserId() {
        Number isNumber = (Number) ThreadLocalUtil.get("userId");
        return isNumber.longValue();
    }
}