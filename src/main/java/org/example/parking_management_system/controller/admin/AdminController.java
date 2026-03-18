package org.example.parking_management_system.controller.admin;

import io.jsonwebtoken.Claims;
import org.example.parking_management_system.dto.admin.response.AdminDetailsDTO;
import org.example.parking_management_system.dto.admin.request.AdminLoginDTO;
import org.example.parking_management_system.exception.BusinessException;
import org.example.parking_management_system.service.admin.AdminService;
import org.example.parking_management_system.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody AdminLoginDTO adminLoginDTO) {
        AdminLoginDTO response = adminService.login(adminLoginDTO);
        Map<String,Object> claims = new HashMap<>();

        claims.put("id",response.getId());
        claims.put("username",response.getUsername());
        String token = JwtUtil.createToken(claims, response.getRole());
        
        // 构建返回数据对象
        Map<String, Object> data = new HashMap<>();
        data.put("id", response.getId());
        data.put("username", response.getUsername());
        data.put("realName", response.getRealName());
        data.put("role", response.getRole());
        data.put("token", token);
        
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "登录成功");
        result.put("data", data);

        return result;
    }
    
    @GetMapping("/profile")
    public Map<String,Object> adminDetailsById(@RequestHeader(name="Authorization") String token){
        Claims claims = JwtUtil.extractAllClaims(token);
        Long id = claims.get("id", Long.class);
        AdminDetailsDTO adminDetailsDTO = adminService.findById(id);
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("message","查询成功");
        result.put("data", adminDetailsDTO);
        return result;
    }
    
    @PostMapping("/logout")
    public Map<String,Object> logout(@RequestHeader(name = "Authorization") String token){
        if (!JwtUtil.isTokenBlacklisted(token)){
            JwtUtil.blacklistToken(token);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("code",200);
        result.put("message","退出成功");
        result.put("data", null);
        return result;
    }

}