package org.example.parking_management_system.controller;

import org.example.parking_management_system.dto.admin.request.UpdateVehicleDTO;
import org.example.parking_management_system.dto.request.VehicleAddDTO;
import org.example.parking_management_system.dto.response.VehicleAddDetailsDTO;
import org.example.parking_management_system.dto.response.VehicleDetailsDTO;
import org.example.parking_management_system.dto.response.VehicleUpdateDetailsDTO;
import org.example.parking_management_system.service.VehicleService;
import org.example.parking_management_system.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class VehicleCoontroller {
    @Autowired
    private VehicleService vehicleService;
    @PostMapping("/vehicle")
    public Map<String,Object> addVehicle(@RequestBody VehicleAddDTO vehicleAddDTO){
        Map<String,Object> result = new HashMap<>();
        Long userId = UserUtil.getCurrentUserId();
        VehicleAddDetailsDTO vehicleAddDetailsDTO = vehicleService.addVehicle(userId,vehicleAddDTO);
        result.put("code",200);
        result.put("message","车辆添加成功");
        result.put("data",vehicleAddDetailsDTO);
        return result;
    }
    @DeleteMapping("/vehicle/{id}")
    public Map<String,Object> deleteVehicle(@PathVariable Long id){
        Map<String,Object> result = new HashMap<>();
        vehicleService.deleteVehicle(id);
        result.put("code",200);
        result.put("message","车辆删除成功");
        result.put("data",null);
        return result;
    }
    @PutMapping("/vehicle/{id}")
    public Map<String,Object> updateVehicle(@PathVariable Long id, @RequestBody UpdateVehicleDTO updateVehicleDTO){
        Map<String,Object> result = new HashMap<>();
        Long userId = UserUtil.getCurrentUserId();
        VehicleUpdateDetailsDTO vehicleUpdateDetailsDTO = vehicleService.updateVehicle(userId,id,updateVehicleDTO);
        result.put("code",200);
        result.put("message","车辆更新成功");
        result.put("data",vehicleUpdateDetailsDTO);
        return result;
    }
    @GetMapping("/vehicle")
    public Map<String,Object> getVehicleAll(){
        Map<String,Object> result = new HashMap<>();
        Long userId = UserUtil.getCurrentUserId();
        List<VehicleDetailsDTO> vehicleDetailsDTOS = vehicleService.findByUserId(userId);
        result.put("code",200);
        result.put("message","查询成功");
        result.put("data", vehicleDetailsDTOS);
        return result;
    }
    @GetMapping("/vehicle/{id}")
    public Map<String,Object> getVehicleDetails(@PathVariable Long id){
        Map<String,Object> result = new HashMap<>();
        Long userId = UserUtil.getCurrentUserId();
        VehicleDetailsDTO vehicleDetailsDTO = vehicleService.findById(userId,id);
        result.put("code",200);
        result.put("message","查询成功");
        result.put("data",vehicleDetailsDTO);
        return result;
    }
}