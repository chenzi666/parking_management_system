package org.example.parking_management_system.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * MD5加密工具类
 * 提供字符串MD5加密功能
 */
public class Md5Util {
    
    // 固定盐值
    private static final String FIXED_SALT = "parking_system_salt2025";
    
    /**
     * 对字符串进行MD5加密
     * 
     * @param input 待加密的字符串
     * @return 加密后的MD5值，32位小写字符串
     */
    public static String encrypt(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        
        return DigestUtils.md5Hex(input);
    }
    
    /**
     * 对字符串进行MD5加密（加盐）
     * 
     * @param input 待加密的字符串
     * @param salt 盐值
     * @return 加密后的MD5值，32位小写字符串
     */
    public static String encrypt(String input, String salt) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        
        if (salt == null) {
            salt = "";
        }
        
        return DigestUtils.md5Hex(input + salt);
    }
    
    /**
     * 使用固定盐值对字符串进行MD5加密
     * 
     * @param input 待加密的字符串
     * @return 加密后的MD5值，32位小写字符串
     */
    public static String encryptWithFixedSalt(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        
        return encrypt(input, FIXED_SALT);
    }

    /**
     * 对Md5加密后密码进行验证
     * @param input
     * @param storedHash
     * @return
     */
    public static boolean verifyWithFixedSalt(String input, String storedHash) {
        if (input == null || storedHash == null) {
            return false;
        }

        String encryptedInput = encryptWithFixedSalt(input);
        return encryptedInput.equals(storedHash);
    }
}