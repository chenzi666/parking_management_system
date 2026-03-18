package org.example.parking_management_system.mapper;

import org.example.parking_management_system.dto.request.UserRegisterDTO;
import org.example.parking_management_system.entity.User;

public interface UserMapper {
    User findByPhone(String phone);

    int register(UserRegisterDTO registerDTO);

    User findById(Long id);

    void updatePassword(Long id, String newPassword);
}
