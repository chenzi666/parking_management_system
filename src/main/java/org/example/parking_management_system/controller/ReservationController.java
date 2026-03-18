package org.example.parking_management_system.controller;

import org.example.parking_management_system.dto.request.ReservationCreateDTO;
import org.example.parking_management_system.dto.request.ReservationQueryDTO;
import org.example.parking_management_system.dto.response.ReservationCreateResponseDTO;
import org.example.parking_management_system.dto.response.ReservationDetailsDTO;
import org.example.parking_management_system.dto.response.ReservationListDTO;
import org.example.parking_management_system.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/reservation")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    /**
     * 创建预约
     * @param reservationCreateDTO 预约创建请求DTO
     * @return 包含预约创建响应的Map
     */
    @PostMapping
    public Map<String, Object> createReservation(@RequestBody ReservationCreateDTO reservationCreateDTO) {
        // 获取当前用户ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.parseLong(authentication.getName());
        
        ReservationCreateResponseDTO response = reservationService.createReservation(userId, reservationCreateDTO);
        return Map.of(
            "code", 200,
            "message", "预约创建成功",
            "data", response
        );
    }

    /**
     * 取消预约
     * @param id 预约ID
     * @return 包含操作结果的Map
     */
    @PutMapping("/{id}/cancel")
    public Map<String, Object> cancelReservation(@PathVariable Long id) {
        // 获取当前用户ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.parseLong(authentication.getName());
        
        reservationService.cancelReservation(userId, id);
        return Map.of(
            "code", 200,
            "message", "预约取消成功",
            "data", null
        );
    }

    /**
     * 获取我的预约列表
     * @param reservationQueryDTO 预约查询请求DTO
     * @return 包含预约列表的Map
     */
    @GetMapping
    public Map<String, Object> getMyReservations(ReservationQueryDTO reservationQueryDTO) {
        // 获取当前用户ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.parseLong(authentication.getName());
        
        ReservationListDTO response = reservationService.getMyReservations(userId, reservationQueryDTO);
        return Map.of(
            "code", 200,
            "message", "获取预约列表成功",
            "data", response
        );
    }

    /**
     * 获取预约详情
     * @param id 预约ID
     * @return 包含预约详情的Map
     */
    @GetMapping("/{id}")
    public Map<String, Object> getReservationDetails(@PathVariable Long id) {
        // 获取当前用户ID
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = Long.parseLong(authentication.getName());
        
        ReservationDetailsDTO response = reservationService.getReservationDetails(userId, id);
        return Map.of(
            "code", 200,
            "message", "获取预约详情成功",
            "data", response
        );
    }
}
