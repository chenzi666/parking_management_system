package org.example.parking_management_system.controller.admin;

import org.example.parking_management_system.dto.admin.request.AddAreaDTO;
import org.example.parking_management_system.dto.admin.request.UpdateAreaDTO;
import org.example.parking_management_system.dto.admin.response.AddAreaDetailsDTO;
import org.example.parking_management_system.dto.admin.response.QueryAreaDTO;
import org.example.parking_management_system.dto.admin.response.UpdateAreaDetailsDTO;
import org.example.parking_management_system.service.admin.AdminAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminAreaController {
    @Autowired
    private AdminAreaService adminAreaService;
    @GetMapping("/parking-lot/{parkingLotId}/area")
    public Map<String, Object> getAreaByAll(@PathVariable Long parkingLotId){
        Map<String,Object> result = new HashMap<>();
        List<QueryAreaDTO> queryAreaDTOS = adminAreaService.findByParkingLotIdAll(parkingLotId);
        result.put("code",200);
        result.put("message","查询成功");
        result.put("data",queryAreaDTOS);
        return result;
    }
    @PostMapping("/parking-lot/{parkingLotId}/area")
    public Map<String,Object> addArea(@PathVariable Long parkingLotId, @RequestBody AddAreaDTO addAreaDTO){
        Map<String,Object> result = new HashMap<>();
       AddAreaDetailsDTO addAreaDetailsDTO = adminAreaService.insert(parkingLotId,addAreaDTO);
       result.put("code",200);
       result.put("message","区域添加成功");
       result.put("data",addAreaDetailsDTO);
       return result;
    }
    @PutMapping("/area/{id}")
    public Map<String,Object> updateArea(@PathVariable Long id,@RequestBody UpdateAreaDTO areaDTO){
        Map<String,Object> result = new HashMap<>();
        UpdateAreaDetailsDTO updateAreaDetailsDTO = adminAreaService.updateById(id,areaDTO);
        result.put("code",200);
        result.put("message","区域更新成功");
        result.put("data",updateAreaDetailsDTO);
        return result;
    }
    @DeleteMapping("/area/{id}")
    public Map<String,Object> deleteArea(@PathVariable Long id){
        Map<String,Object> result = new HashMap<>();
        adminAreaService.deleteById(id);
        result.put("code" ,200);
        result.put("message","区域删除成功");
        result.put("data",null);
        return result;
    }
}
