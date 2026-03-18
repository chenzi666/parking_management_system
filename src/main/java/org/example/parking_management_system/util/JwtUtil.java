package org.example.parking_management_system.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.Objects;

/**
 * JWT工具类
 * 提供JWT令牌的生成、解析和验证功能
 */
public class JwtUtil {
    
    // JWT密钥
    private static final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    // JWT过期时间（毫秒），默认24小时
    private static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60 * 1000;
    
    // Token黑名单集合
    private static final Set<String> BLACKLISTED_TOKENS = ConcurrentHashMap.newKeySet();
    
    /**
     * 从JWT令牌中提取用户名
     * 
     * @param token JWT令牌
     * @return 用户名
     */
    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    /**
     * 从JWT令牌中提取过期时间
     * 
     * @param token JWT令牌
     * @return 过期时间
     */
    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    
    /**
     * 从JWT令牌中提取特定声明
     * 
     * @param token JWT令牌
     * @param claimsResolver 声明解析器
     * @param <T> 声明类型
     * @return 声明值
     */
    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    /**
     * 解析JWT令牌中的所有声明
     * 
     * @param token JWT令牌
     * @return 所有声明
     */
    public static Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).getBody();
    }
    
    /**
     * 检查JWT令牌是否过期
     * 
     * @param token JWT令牌
     * @return 是否过期
     */
    private static Boolean isTokenExpired(String token) {
        final Date expiration = extractExpiration(token);
        return expiration.before(new Date());
    }
    
    /**
     * 检查JWT令牌是否在黑名单中
     * 
     * @param token JWT令牌
     * @return 是否在黑名单中
     */
    public static Boolean isTokenBlacklisted(String token) {
        return BLACKLISTED_TOKENS.contains(token);
    }
    
    /**
     * 生成JWT令牌
     * 
     * @param adminUser 管理员用户信息
     * @return JWT令牌
     */
    public String generateToken(org.example.parking_management_system.entity.AdminUser adminUser) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, adminUser.getUsername());
    }
        /**
     * 生成用户JWT令牌
     *
     * @param user 用户信息
     * @return JWT令牌
     */
//    public static String generateUserToken(org.example.parking_management_system.entity.User user) {
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("userId", user.getId());
//        claims.put("userType", "user");
//        return createToken(claims, user.getNickname());
//    }
//
//    public static Boolean validateUserToken(String token, org.example.parking_management_system.entity.User user) {
//        final String username = extractUsername(token);
//        return (username.equals(user.getNickname()) && !isTokenExpired(token) && !isTokenBlacklisted(token));
//    }

    /**
     * 创建JWT令牌
     * 
     * @param claims 声明
     * @param subject 主题（用户名）
     * @return JWT令牌
     */
    public static String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(SECRET_KEY)
                .compact();
    }
    
    /**
     * 验证JWT令牌
     * 
     * @param token JWT令牌
     * @param adminUser 管理员用户信息
     * @return 是否有效
     */
    public static Boolean validateToken(String token, org.example.parking_management_system.entity.AdminUser adminUser) {
        final String username = extractUsername(token);
        return (username.equals(adminUser.getUsername()) && !isTokenExpired(token) && !isTokenBlacklisted(token));
    }
    
    /**
     * 将JWT令牌加入黑名单
     * 
     * @param token 需要加入黑名单的JWT令牌
     */
    public static void blacklistToken(String token) {
        BLACKLISTED_TOKENS.add(token);
    }
    
    /**
     * 从黑名单中移除JWT令牌
     * 
     * @param token 需要从黑名单中移除的JWT令牌
     */
    public static void unblacklistToken(String token) {
        BLACKLISTED_TOKENS.remove(token);
    }
    
    /**
     * 获取黑名单中的令牌数量
     * 
     * @return 黑名单中的令牌数量
     */
    public static int getBlacklistedTokenCount() {
        return BLACKLISTED_TOKENS.size();
    }

}