package org.example.parking_management_system.controller.admin;

import org.example.parking_management_system.dto.admin.request.AddParkingSpaceDTO;
import org.example.parking_management_system.dto.admin.request.BatchAddParkingSpaceDTO;
import org.example.parking_management_system.dto.admin.request.QueryParkingSpaceDTO;
import org.example.parking_management_system.dto.admin.request.UpdateParkingSpaceDTO;
import org.example.parking_management_system.dto.admin.response.AddParkingSpaceCountDTO;
import org.example.parking_management_system.dto.admin.response.ParkingSpaceDetailsDTO;
import org.example.parking_management_system.dto.admin.response.ParkingSpaceListDTO;
import org.example.parking_management_system.service.admin.AdminParkingSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminParkingSpaceController {
    @Autowired
    private AdminParkingSpaceService adminParkingSpaceService;
    
    @GetMapping("/parking-space")
    public Map<String,Object> getParkingSpace(QueryParkingSpaceDTO queryDTO){
        // 设置默认分页参数
        if (queryDTO.getPage() == null || queryDTO.getPage() < 1) {
            queryDTO.setPage(1);
        }
        if (queryDTO.getPageSize() == null || queryDTO.getPageSize() < 1) {
            queryDTO.setPageSize(10);
        }
        
        ParkingSpaceListDTO resultData = adminParkingSpaceService.findByAll(queryDTO);
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("message","查询成功");
        result.put("data", resultData);
        return result;
    }
    
    @PostMapping("/parking-space")
    public Map<String,Object> addParkingSpace(@RequestBody AddParkingSpaceDTO addParkingSpaceDTO){
        ParkingSpaceDetailsDTO parkingSpaceDetailsDTO =  adminParkingSpaceService.addParkingSpace(addParkingSpaceDTO);
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("message","车位添加成功");
        result.put("data",parkingSpaceDetailsDTO);
        return result;
    }
    
    @PostMapping("/parking-space/batch")
    public Map<String,Object> batchAddParkingSpace(@RequestBody ArrayList<BatchAddParkingSpaceDTO> batchAddParkingSpaceDTOS){
        AddParkingSpaceCountDTO count = adminParkingSpaceService.batchAddParkingSpace(batchAddParkingSpaceDTOS);
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("message","批量添加成功");
        result.put("data",count);
        return result;
    }
    
    @PutMapping("/parking-space/{id}")
    public Map<String,Object> updateParkingSpace(@PathVariable Long id, @RequestBody UpdateParkingSpaceDTO dto){
        adminParkingSpaceService.updateParkingSpace(id,dto);
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("message","车位信息更新成功");
        result.put("data",null);
        return result;
    }
    
    @DeleteMapping("/parking-space/{id}")
    public Map<String,Object> delParkingSpace(@PathVariable Long id){
        adminParkingSpaceService.delById(id);
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("message","车位删除成功");
        result.put("data",null);
        return result;
    }
}