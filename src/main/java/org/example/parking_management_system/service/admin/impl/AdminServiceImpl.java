package org.example.parking_management_system.service.admin.impl;

import org.example.parking_management_system.dto.admin.request.AdminLoginDTO;
import org.example.parking_management_system.dto.admin.response.AdminDetailsDTO;
import org.example.parking_management_system.entity.AdminUser;
import org.example.parking_management_system.exception.AuthenticationException;
import org.example.parking_management_system.mapper.admin.AdminMapper;
import org.example.parking_management_system.service.admin.AdminService;
import org.example.parking_management_system.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    
    @Override
    public AdminLoginDTO login(AdminLoginDTO adminLoginDTO) {
        if (adminLoginDTO == null || adminLoginDTO.getUsername() == null || adminLoginDTO.getPassword() == null){
            throw new AuthenticationException("账号或密码不能为空");
        }
        
        AdminUser adminUser = adminMapper.login(adminLoginDTO.getUsername());
        if (adminUser == null) {
            throw new AuthenticationException("账号或密码错误");
        }
        
        boolean verify = Md5Util.verifyWithFixedSalt(adminLoginDTO.getPassword(), adminUser.getPassword());
        if (!verify){
            throw new AuthenticationException("账号或密码错误");
        }
        
        // 构建返回对象
        AdminLoginDTO response = new AdminLoginDTO();
        response.setId(adminUser.getId());
        response.setUsername(adminUser.getUsername());
        response.setRealName(adminUser.getRealName());
        response.setRole(adminUser.getRole());
        
        return response;
    }

    @Override
    public AdminDetailsDTO findById(Long id) {
        return adminMapper.findById(id);
    }
}