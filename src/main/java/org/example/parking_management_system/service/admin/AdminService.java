package org.example.parking_management_system.service.admin;

import org.example.parking_management_system.dto.admin.request.AdminLoginDTO;
import org.example.parking_management_system.dto.admin.response.AdminDetailsDTO;

public interface AdminService {
    AdminLoginDTO login(AdminLoginDTO adminLoginDTO);

    AdminDetailsDTO findById(Long id);
}
