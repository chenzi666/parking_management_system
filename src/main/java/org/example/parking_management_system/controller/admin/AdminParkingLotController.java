package org.example.parking_management_system.controller.admin;

import org.example.parking_management_system.dto.admin.request.AddParkingLotDTO;
import org.example.parking_management_system.dto.admin.request.QueryParkingLotDTO;
import org.example.parking_management_system.dto.admin.request.UpdateParkingLotDTO;
import org.example.parking_management_system.dto.admin.response.AddParkingLotDetailsDTO;
import org.example.parking_management_system.dto.admin.response.ParkingLotListDTO;
import org.example.parking_management_system.dto.admin.response.UpdateParkingLotDetailsDTO;
import org.example.parking_management_system.service.admin.AdminParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminParkingLotController {
    @Autowired
    private AdminParkingLotService adminParkingLotService;
    @GetMapping("/parking-lot")
    public Map<String,Object> getParkingLotAll(QueryParkingLotDTO queryDTO){
        // 设置默认分页参数
        if (queryDTO.getPage() == null || queryDTO.getPage() < 1) {
            queryDTO.setPage(1);
        }
        if (queryDTO.getPageSize() == null || queryDTO.getPageSize() < 1) {
            queryDTO.setPageSize(10);
        }
        
        ParkingLotListDTO resultData = adminParkingLotService.findByAll(queryDTO);
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("message","查询成功");
        result.put("data",resultData);
        return result;
    }
    @PostMapping("/parking-lot")
    public Map<String,Object> addParkingLot(@RequestBody AddParkingLotDTO addParkingLotDTO){
        Map<String,Object> result = new HashMap<>();
        AddParkingLotDetailsDTO addParkingLotDetailsDTO = adminParkingLotService.insert(addParkingLotDTO);
        result.put("code",200);
        result.put("message","添加成功");
        result.put("data",addParkingLotDetailsDTO);
        return result;
    }
    @PutMapping("/parking-lot/{id}")
    public Map<String,Object> updateParkingLot(@PathVariable Long id, @RequestBody UpdateParkingLotDTO updateParkingLotDTO){
        Map<String,Object> result = new HashMap<>();
        UpdateParkingLotDetailsDTO updateParkingLotDetailsDTO = adminParkingLotService.updateById(id,updateParkingLotDTO);
        result.put("code",200);
        result.put("message","修改成功");
        result.put("data",updateParkingLotDetailsDTO);
        return result;
    }
    @DeleteMapping("/parking-lot/{id}")
    public Map<String,Object> delParkingLot (@PathVariable Long id){
        Map<String,Object> result = new HashMap<>();
       adminParkingLotService.deleteById(id);
        result.put("code",200);
        result.put("message","删除成功");
        result.put("data",null);
        return result;
    }
}
