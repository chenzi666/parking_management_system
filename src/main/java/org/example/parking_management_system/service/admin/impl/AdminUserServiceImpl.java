package org.example.parking_management_system.service.admin.impl;


import org.example.parking_management_system.dto.admin.request.AddAdminUserDTO;
import org.example.parking_management_system.dto.admin.request.QueryAdminUserDTO;
import org.example.parking_management_system.dto.admin.request.UpdateAdminPasswordDTO;
import org.example.parking_management_system.dto.admin.request.UpdateAdminUserDTO;
import org.example.parking_management_system.dto.admin.response.AddAdminUserDetailsDTO;
import org.example.parking_management_system.dto.admin.response.AdminUserDTO;
import org.example.parking_management_system.dto.admin.response.AdminUserItemDTO;
import org.example.parking_management_system.dto.admin.response.UpdateAdminUserDetailsDTO;
import org.example.parking_management_system.entity.AdminUser;
import org.example.parking_management_system.exception.BusinessException;
import org.example.parking_management_system.mapper.admin.AdminOperationLogMapper;
import org.example.parking_management_system.mapper.admin.AdminUserMapper;
import org.example.parking_management_system.service.admin.AdminUserService;
import org.example.parking_management_system.util.Md5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserServiceImpl implements AdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;
    @Autowired
    private AdminOperationLogMapper adminOperationLogMapper;

    private String roleText(String role){
        return switch (role) {
            case "manager" -> "经理";
            case "operator" -> "操作员";
            case "admin" -> "超级管理员";
            default -> "未知";
        };
    }
    private String statusText(Integer status){
        return switch (status) {
            case 1 -> "启用";
            case 0 -> "禁用";
            default -> "未知";
        };
    }
    @Override
    public AdminUserItemDTO findAll(QueryAdminUserDTO queryAdminUserDTO) {
        if (queryAdminUserDTO.getPage() == null || queryAdminUserDTO.getPageSize() == null){
            throw new BusinessException("页码或页数不能为空");
        }
        Integer page = queryAdminUserDTO.getPage();
        Integer pageSize = queryAdminUserDTO.getPageSize();
        if (page < 1){
            page = 1;
        }
        if (pageSize <= 0){
            pageSize = 10;
        }else if (pageSize > 100){
            pageSize = 100;
        }
        Integer offset = (page-1)* pageSize;
        queryAdminUserDTO.setOffset(offset);
       List<AdminUser> adminUsers = adminUserMapper.findAll(queryAdminUserDTO);
       AdminUserItemDTO adminUserItemDTO = new AdminUserItemDTO();
       for (AdminUser adminUser:adminUsers){
           AdminUserDTO dto = new AdminUserDTO();
           BeanUtils.copyProperties(adminUser,dto);
           dto.setRoleText(roleText(adminUser.getRole()));
           dto.setStatusText(statusText(adminUser.getStatus()));
           adminUserItemDTO.getList().add(dto);
       }
       adminUserItemDTO.setPage(page);
       adminUserItemDTO.setPageSize(pageSize);

       int total = adminUserMapper.count();
       adminUserItemDTO.setTotal(total);
        return adminUserItemDTO;
    }

    @Override
    public AddAdminUserDetailsDTO insert(AddAdminUserDTO addAdminUserDTO) {
        if (addAdminUserDTO == null){
            throw new BusinessException("数据为空");
        }
        AdminUser adminName = adminUserMapper.findByUsername(addAdminUserDTO.getUsername());
        if (adminName != null){
            throw new BusinessException("用户名已存在");
        }
        String password = Md5Util.encryptWithFixedSalt(addAdminUserDTO.getPassword());
        addAdminUserDTO.setPassword(password);
        Long id = adminUserMapper.insert(addAdminUserDTO);
        if (id == 0){
            throw new BusinessException("管理员添加失败");
        }
        AdminUser adminUser = adminUserMapper.findById(addAdminUserDTO.getId());
        AddAdminUserDetailsDTO addAdminUserDetailsDTO = new AddAdminUserDetailsDTO();
        BeanUtils.copyProperties(adminUser,addAdminUserDetailsDTO);
        return addAdminUserDetailsDTO;
    }

    @Override
    public UpdateAdminUserDetailsDTO updateById(Long id, UpdateAdminUserDTO updateAdminUserDTO) {
        if (id == null || updateAdminUserDTO == null){
            throw new BusinessException("id或数据不能为空");
        }
        AdminUser adminUser = adminUserMapper.findById(id);
        if (adminUser == null){
            throw new BusinessException("用户不存在");
        }
        adminUserMapper.updateById(id,updateAdminUserDTO);
        AdminUser updateadminUser = adminUserMapper.findById(id);
        UpdateAdminUserDetailsDTO updateAdminUserDetailsDTO = new UpdateAdminUserDetailsDTO();
        BeanUtils.copyProperties(updateadminUser,updateAdminUserDetailsDTO);
        return updateAdminUserDetailsDTO;
    }

    @Override
    public void updatePassword(Long id, UpdateAdminPasswordDTO updateAdminPasswordDTO) {
        if (id == null || updateAdminPasswordDTO.getPassword() == null){
            throw new BusinessException("id或数据不能为空");
        }
        System.out.println(updateAdminPasswordDTO.getPassword());
        AdminUser adminUser = adminUserMapper.findById(id);
        if (adminUser == null){
            throw new BusinessException("用户不存在");
        }
        String password = Md5Util.encryptWithFixedSalt(updateAdminPasswordDTO.getPassword());
        adminUserMapper.updatePassword(id,password);
    }

    @Override
    public void deleteById(Long id) {
        if (id == null){
            throw new BusinessException("id不能为空");
        }
        AdminUser adminUser = adminUserMapper.findById(id);
        if (adminUser == null){
            throw new BusinessException("用户不存在");
        }
        adminOperationLogMapper.deleteByAdminId(id);
        adminUserMapper.deleteById(id);
    }
}
