package org.example.parking_management_system.interceptors;

import io.jsonwebtoken.Claims;
import org.example.parking_management_system.entity.AdminUser;
import org.example.parking_management_system.util.JwtUtil;
import org.example.parking_management_system.util.ThreadLocalUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Map;
import java.util.Objects;

@Component
public class LoginInterception implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求路径
        String requestURI = request.getRequestURI();
        
        // 从请求头中获取token
        String token = request.getHeader("Authorization");
        // 如果没有token，返回未授权
        if (token == null || token.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"code\": 401, \"message\": \"未提供认证令牌\"}");
            return false;
        }
        
        try {
            // 提取用户名
            String username = JwtUtil.extractUsername(token);
            String sub = JwtUtil.extractClaim(token, Claims -> Claims.get("sub", String.class));
            Map<String,Object> map = JwtUtil.extractAllClaims(token);
            ThreadLocalUtil.set("userId",map.get("id"));
            ThreadLocalUtil.set("token",token);
            // 检查token是否在黑名单中
            if (JwtUtil.isTokenBlacklisted(token)) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"code\": 401, \"message\": \"令牌已过期或无效\"}");
                return false;
            }

            // 验证token是否过期（通过尝试提取声明来间接判断）
            try {
                JwtUtil.extractAllClaims(token);
            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"code\": 401, \"message\": \"令牌已过期或无效\"}");
                return false;
            }
            if (requestURI.contains("admin")){
                if (!Objects.equals(sub, "admin") &&
                    !Objects.equals(sub, "manager") &&
                    !Objects.equals(sub, "operator")) {
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    response.getWriter().write("{\"code\": 401, \"message\": \"无权访问\"}");
                    return false;
                }
            }
            // 这里可以添加更多的验证逻辑，比如从数据库查询用户信息进行验证
            // 由于是演示，我们只验证token的基本有效性
            
            // token有效，放行
            return true;
        } catch (Exception e) {
            // token解析失败
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"code\": 401, \"message\": \"无效的认证令牌\"}");
            return false;
        }
    }
}