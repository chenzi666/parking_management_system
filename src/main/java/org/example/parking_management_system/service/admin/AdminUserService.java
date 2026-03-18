package org.example.parking_management_system.service.admin;

import org.example.parking_management_system.dto.admin.request.AddAdminUserDTO;
import org.example.parking_management_system.dto.admin.request.QueryAdminUserDTO;
import org.example.parking_management_system.dto.admin.request.UpdateAdminPasswordDTO;
import org.example.parking_management_system.dto.admin.request.UpdateAdminUserDTO;
import org.example.parking_management_system.dto.admin.response.AddAdminUserDetailsDTO;
import org.example.parking_management_system.dto.admin.response.AdminUserItemDTO;
import org.example.parking_management_system.dto.admin.response.UpdateAdminUserDetailsDTO;

public interface AdminUserService {
    AdminUserItemDTO findAll(QueryAdminUserDTO queryAdminUserDTO);

    AddAdminUserDetailsDTO insert(AddAdminUserDTO addAdminUserDTO);

    UpdateAdminUserDetailsDTO updateById(Long id, UpdateAdminUserDTO updateAdminUserDTO);

    void updatePassword(Long id, UpdateAdminPasswordDTO updateAdminPasswordDTO);

    void deleteById(Long id);
}
