package org.example.parking_management_system.controller.admin;

import com.sun.jdi.event.StepEvent;
import org.example.parking_management_system.dto.admin.request.AddAdminUserDTO;
import org.example.parking_management_system.dto.admin.request.QueryAdminUserDTO;
import org.example.parking_management_system.dto.admin.request.UpdateAdminPasswordDTO;
import org.example.parking_management_system.dto.admin.request.UpdateAdminUserDTO;
import org.example.parking_management_system.dto.admin.response.AddAdminUserDetailsDTO;
import org.example.parking_management_system.dto.admin.response.AdminUserItemDTO;
import org.example.parking_management_system.dto.admin.response.UpdateAdminUserDetailsDTO;
import org.example.parking_management_system.service.admin.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;
    @GetMapping("/user")
    public Map<String,Object> getAdminUser(QueryAdminUserDTO queryAdminUserDTO){
        Map<String,Object> result = new HashMap<>();
        AdminUserItemDTO adminUserItemDTO = adminUserService.findAll(queryAdminUserDTO);
        result.put("code",200);
        result.put("message","查询成功");
        result.put("data",adminUserItemDTO);
        return result;
    }
    @PostMapping("/user")
    public Map<String,Object> addAdminUser(@RequestBody AddAdminUserDTO addAdminUserDTO){
        Map<String,Object> result = new HashMap<>();
        AddAdminUserDetailsDTO addAdminUserDetailsDTO = adminUserService.insert(addAdminUserDTO);
        result.put("code",200);
        result.put("message","管理员添加成功");
        result.put("data",addAdminUserDetailsDTO);
        return result;
    }
    @PutMapping("/user/{id}")
    public Map<String,Object> updateAdminUser(@PathVariable Long id, UpdateAdminUserDTO updateAdminUserDTO){
        Map<String,Object> result = new HashMap<>();
        UpdateAdminUserDetailsDTO updateAdminUserDetailsDTO = adminUserService.updateById(id,updateAdminUserDTO);
        result.put("code",200);
        result.put("message","管理员信息更新成功");
        result.put("data",updateAdminUserDetailsDTO);
        return result;
    }
    @PutMapping("/user/{id}/password")
    public Map<String,Object> updateAdminPassword(@PathVariable Long id, @RequestBody UpdateAdminPasswordDTO updateAdminPasswordDTO){
        Map<String,Object> result = new HashMap<>();
        adminUserService.updatePassword(id,updateAdminPasswordDTO);
        result.put("code",200);
        result.put("message","密码修改成功");
        result.put("data",null);
        return result;
    }
    @DeleteMapping("/user/{id}")
    public Map<String,Object> deleteAdminUser(@PathVariable Long id){
        Map<String,Object> result = new HashMap<>();
        adminUserService.deleteById(id);
        result.put("code",200);
        result.put("message","管理员删除成功");
        result.put("data",null);
        return result;
    }
}
