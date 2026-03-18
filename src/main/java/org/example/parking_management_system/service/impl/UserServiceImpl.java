package org.example.parking_management_system.service.impl;

import org.example.parking_management_system.dto.request.UserLoginDTO;
import org.example.parking_management_system.dto.request.UserRegisterDTO;
import org.example.parking_management_system.dto.request.UserUpdatePasswordDTO;
import org.example.parking_management_system.dto.response.UserLoginDetailsDTO;
import org.example.parking_management_system.dto.response.UserRegisterDetailsDTO;
import org.example.parking_management_system.entity.User;
import org.example.parking_management_system.exception.BusinessException;
import org.example.parking_management_system.mapper.UserMapper;
import org.example.parking_management_system.service.UserService;
import org.example.parking_management_system.util.JwtUtil;
import org.example.parking_management_system.util.Md5Util;
import org.example.parking_management_system.util.ThreadLocalUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserRegisterDetailsDTO register(UserRegisterDTO registerDTO) {
        if (registerDTO.getPhone() == null || registerDTO.getPassword() == null){
            throw new BusinessException("手机号或密码不能为空");
        }
        User userPhone = userMapper.findByPhone(registerDTO.getPhone());
        if (userPhone != null){
            throw new BusinessException("手机号已注册");
        }
        String password = Md5Util.encryptWithFixedSalt(registerDTO.getPassword());
        registerDTO.setPassword(password);
        int register = userMapper.register(registerDTO);
        if (register != 1){
            throw new BusinessException("注册失败");
        }

        User user = userMapper.findById(registerDTO.getId());

        Map<String,Object> claims = new HashMap<>();
        claims.put("id",user.getId());
        claims.put("username",user.getNickname());
        String token = JwtUtil.createToken(claims,user.getMembershipLevel().toString());

        UserRegisterDetailsDTO userRegisterDetailsDTO = new UserRegisterDetailsDTO();
        BeanUtils.copyProperties(user,userRegisterDetailsDTO);
        userRegisterDetailsDTO.setToken(token);
        return userRegisterDetailsDTO;
    }

    @Override
    public UserLoginDetailsDTO login(UserLoginDTO userLoginDTO) {
        if (userLoginDTO.getPhone() == null || userLoginDTO.getPassword() == null){
            throw new BusinessException("账号或密码不能为空");
        }
        User userPhone = userMapper.findByPhone(userLoginDTO.getPhone());
        if (userPhone == null){
            throw new BusinessException("手机号为注册");
        }
        if (!Md5Util.verifyWithFixedSalt(userLoginDTO.getPassword(),userPhone.getPassword())){
            throw new BusinessException("密码错误");
        }
        UserLoginDetailsDTO userLoginDetailsDTO = new UserLoginDetailsDTO();
        BeanUtils.copyProperties(userPhone,userLoginDetailsDTO);

        Map<String,Object> claims = new HashMap<>();
        claims.put("id",userPhone.getId());
        claims.put("username",userPhone.getNickname());
        String token = JwtUtil.createToken(claims,userPhone.getMembershipLevel().toString());
        userLoginDetailsDTO.setToken(token);
        return userLoginDetailsDTO;
    }

    @Override
    public void updatePassword(Long id, UserUpdatePasswordDTO updatePasswordDTO) {
        if (updatePasswordDTO.getNewPassword() == null || updatePasswordDTO.getOldPassword() == null){
            throw new BusinessException("密码不能为空");
        }
        User user = userMapper.findById(id);
        if (!Md5Util.verifyWithFixedSalt(updatePasswordDTO.getOldPassword(),user.getPassword())){
            throw new BusinessException("旧密码错误");
        }
        if (Md5Util.verifyWithFixedSalt(updatePasswordDTO.getNewPassword(),user.getPassword())){
            throw new BusinessException("新密码不能和旧密码一致");
        }
        String newPassword = Md5Util.encryptWithFixedSalt(updatePasswordDTO.getNewPassword());
        userMapper.updatePassword(id,newPassword);
        JwtUtil.blacklistToken(ThreadLocalUtil.get("token").toString());
    }
}
