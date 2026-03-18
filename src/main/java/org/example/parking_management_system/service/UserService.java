package org.example.parking_management_system.service;

import org.example.parking_management_system.dto.request.UserLoginDTO;
import org.example.parking_management_system.dto.request.UserRegisterDTO;
import org.example.parking_management_system.dto.request.UserUpdatePasswordDTO;
import org.example.parking_management_system.dto.response.UserLoginDetailsDTO;
import org.example.parking_management_system.dto.response.UserRegisterDetailsDTO;

public interface UserService {
    UserRegisterDetailsDTO register(UserRegisterDTO registerDTO);

    UserLoginDetailsDTO login(UserLoginDTO userLoginDTO);

    void updatePassword(Long id, UserUpdatePasswordDTO updatePasswordDTO);
}
