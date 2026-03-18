package org.example.parking_management_system.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.parking_management_system.dto.request.UserLoginDTO;
import org.example.parking_management_system.dto.request.UserRegisterDTO;
import org.example.parking_management_system.dto.request.UserUpdatePasswordDTO;
import org.example.parking_management_system.dto.response.UserLoginDetailsDTO;
import org.example.parking_management_system.dto.response.UserRegisterDetailsDTO;
import org.example.parking_management_system.service.UserService;
import org.example.parking_management_system.util.ThreadLocalUtil;
import org.example.parking_management_system.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/register")
    public Map<String,Object> userRegister(@RequestBody UserRegisterDTO registerDTO){
        Map<String,Object> result = new HashMap<>();
        UserRegisterDetailsDTO userRegisterDetailsDTO = userService.register(registerDTO);
        result.put("code",200);
        result.put("message","注册成功");
        result.put("data",userRegisterDetailsDTO);
        return result;
    }
    @PostMapping("/login")
    public Map<String,Object> userLogin(@RequestBody UserLoginDTO userLoginDTO){
        Map<String,Object> result = new HashMap<>();
        UserLoginDetailsDTO userLoginDetailsDTO = userService.login(userLoginDTO);
        result.put("code",200);
        result.put("message","登录成功");
        result.put("data",userLoginDetailsDTO);
        return result;
    }
    @PutMapping("/change-password")
    public Map<String,Object> updatePassword(@RequestBody UserUpdatePasswordDTO updatePasswordDTO){
        Map<String,Object> result = new HashMap<>();
        Long userId = UserUtil.getCurrentUserId();
        userService.updatePassword(userId,updatePasswordDTO);
        result.put("code",200);
        result.put("message","修改成功");
        result.put("data",null);
        return result;
    }
}
