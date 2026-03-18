package org.example.parking_management_system.mapper.admin;

import org.apache.ibatis.annotations.Param;
import org.example.parking_management_system.dto.admin.request.AddAdminUserDTO;
import org.example.parking_management_system.dto.admin.request.QueryAdminUserDTO;
import org.example.parking_management_system.dto.admin.request.UpdateAdminUserDTO;
import org.example.parking_management_system.entity.AdminUser;
import org.example.parking_management_system.entity.User;

import java.util.List;

public interface AdminUserMapper {
    /**
     * 根据ID列表批量查询管理员用户
     */
    List<AdminUser> findByIds(@Param("list") List<Long> userIds);
    List<AdminUser> findAll(QueryAdminUserDTO queryAdminUserDTO);

    int count();

    AdminUser findById(Long id);

    Long insert(AddAdminUserDTO addAdminUserDTO);

    AdminUser findByUsername(String username);

    void updateById(Long id, UpdateAdminUserDTO updateAdminUserDTO);

    void updatePassword(Long id, String password);

    void deleteById(Long id);
}