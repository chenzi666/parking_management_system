package org.example.parking_management_system.util;

import java.util.HashMap;
import java.util.Map;

/**
 * ThreadLocal工具类，用于存储当前线程的变量
 */
public class ThreadLocalUtil {
    /**
     * 提供一个ThreadLocal存储空间
     */
    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = new ThreadLocal<Map<String, Object>>() {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };

    /**
     * 获取当前线程的Map
     *
     * @return 当前线程的Map
     */
    public static Map<String, Object> getCurrentThreadMap() {
        return THREAD_LOCAL.get();
    }

    /**
     * 向当前线程的Map中设置值
     *
     * @param key   键
     * @param value 值
     */
    public static void set(String key, Object value) {
        getCurrentThreadMap().put(key, value);
    }

    /**
     * 从当前线程的Map中获取值
     *
     * @param key 键
     * @return 值
     */
    public static Object get(String key) {
        return getCurrentThreadMap().get(key);
    }

    /**
     * 从当前线程的Map中移除指定键的值
     *
     * @param key 键
     */
    public static void remove(String key) {
        getCurrentThreadMap().remove(key);
    }

    /**
     * 清空当前线程的Map
     */
    public static void clear() {
        THREAD_LOCAL.remove();
    }
}