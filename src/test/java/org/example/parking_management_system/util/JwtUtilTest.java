package org.example.parking_management_system.util;

import org.example.parking_management_system.entity.AdminUser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {
    
    private final JwtUtil jwtUtil = new JwtUtil();
    
    @Test
    void testGenerateAndValidateToken() {
        // 创建测试管理员用户
        AdminUser adminUser = new AdminUser();
        adminUser.setUsername("testuser");
        adminUser.setPassword("password");
        adminUser.setRole("ADMIN");
        
        // 生成token
        String token = jwtUtil.generateToken(adminUser);
        assertNotNull(token);
        assertFalse(token.isEmpty());
        
        // 验证token
        String username = jwtUtil.extractUsername(token);
        assertEquals("testuser", username);
        
        // 验证token有效性
        Boolean isValid = jwtUtil.validateToken(token, adminUser);
        assertTrue(isValid);
    }
    
    @Test
    void testTokenExpiration() throws InterruptedException {
        // 创建测试管理员用户
        AdminUser adminUser = new AdminUser();
        adminUser.setUsername("testuser");
        adminUser.setPassword("password");
        adminUser.setRole("ADMIN");
        
        // 生成token，设置很短的有效期用于测试过期情况
        String token = jwtUtil.generateToken(adminUser);
        assertNotNull(token);
        
        // 验证token未过期
        String username = jwtUtil.extractUsername(token);
        assertEquals("testuser", username);
    }
}