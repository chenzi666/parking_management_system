package org.example.parking_management_system.mapper.admin;

import org.example.parking_management_system.dto.admin.response.AdminDetailsDTO;
import org.example.parking_management_system.entity.AdminUser;

public interface AdminMapper {
    AdminUser login(String  username);


    AdminDetailsDTO findById(Long id);
}
