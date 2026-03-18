package org.example.parking_management_system.controller;

import org.example.parking_management_system.dto.request.ParkingLotQueryDTO;
import org.example.parking_management_system.dto.response.ParkingLotDetailsDTO;
import org.example.parking_management_system.dto.response.ParkingLotQueryItemDTO;
import org.example.parking_management_system.dto.response.ParkingStatusItemDTO;
import org.example.parking_management_system.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/")
public class ParkingLotController {
    @Autowired
    private ParkingLotService parkingLotService;
    @GetMapping("/parking-lot/nearby")
    public Map<String,Object> getParkingLotAll(ParkingLotQueryDTO queryDTO){
        Map<String,Object> result = new HashMap<>();
        ParkingLotQueryItemDTO parkingLotQueryItemDTO = parkingLotService.findAll(queryDTO);
        result.put("code",200);
        result.put("message","查询成功");
        result.put("data",parkingLotQueryItemDTO);
        return result;
    }
    @GetMapping("/parking-lot/{id}")
    public Map<String,Object> getParkingLotDetails(@PathVariable Long id){
        Map<String,Object> result = new HashMap<>();
        ParkingLotDetailsDTO parkingLotDetailsDTO = parkingLotService.findById(id);
        result.put("code",200);
        result.put("message","查询成功");
        result.put("data",parkingLotDetailsDTO);
        return result;
    }
    @GetMapping("/parking-lot/{id}/space-status")
    public Map<String,Object> getParkingLotParkingSpace(@PathVariable Long id){
        Map<String,Object> result = new HashMap<>();
       ParkingStatusItemDTO parkingStatusItemDTO = parkingLotService.parkingStatusById(id);
        result.put("code",200);
        result.put("message","查询成功");
        result.put("data",parkingStatusItemDTO);
        return result;
    }
}
