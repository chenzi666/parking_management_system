package org.example.parking_management_system.controller.admin;

import org.example.parking_management_system.dto.admin.request.ImportVehicleDTO;
import org.example.parking_management_system.dto.admin.request.QueryVehicleDTO;
import org.example.parking_management_system.dto.admin.request.UpdateVehicleDTO;
import org.example.parking_management_system.dto.admin.response.VehicleDetailsDTO;
import org.example.parking_management_system.dto.admin.response.VehicleListItemDTO;
import org.example.parking_management_system.exception.BusinessException;
import org.example.parking_management_system.service.admin.AdminVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminVehicleController {
    @Autowired
    private AdminVehicleService adminVehicleService;
    
    @GetMapping("/vehicle")
    public Map<String,Object> getVehicleAll(QueryVehicleDTO queryDTO){
        // 设置默认分页参数
        if (queryDTO.getPage() == null || queryDTO.getPage() < 1) {
            queryDTO.setPage(1);
        }
        if (queryDTO.getPageSize() == null || queryDTO.getPageSize() < 1) {
            queryDTO.setPageSize(10);
        }
        
        Map<String, Object> resultData = adminVehicleService.vehicleAll(queryDTO);
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("message","查询成功");
        result.put("data", resultData);
        return result;
    }
    
    @GetMapping("/vehicle/{id}")
    public Map<String,Object> getVehicleFindById(@PathVariable Long id){
        VehicleDetailsDTO vehicleDetailsDTO = adminVehicleService.findById(id);
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("message","查询成功");
        result.put("data",vehicleDetailsDTO);
        return result;
    }
    
    @PutMapping("/vehicle/{id}")
    public Map<String,Object> updateById(@PathVariable Long id, @RequestBody UpdateVehicleDTO dto){
        VehicleDetailsDTO vehicleDetailsDTO = adminVehicleService.updateById(id,dto);
        if (vehicleDetailsDTO == null){
            throw new BusinessException("修改失败");
        }
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("message","车辆信息更新成功");
        result.put("data",vehicleDetailsDTO);
        return result;
    }
    
    @PostMapping("/vehicle/import")
    public Map<String,Object> vehicleImport(@RequestBody ArrayList<ImportVehicleDTO> importVehicleDTOList){
        List<ImportVehicleDTO> importVehicleDTOS = adminVehicleService.batchImport(importVehicleDTOList);
        if (importVehicleDTOS == null){
            throw new BusinessException("导入失败，导入的数据可能为空");
        }
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("message","导入成功");
        result.put("data",null);
        return result;
    }
}