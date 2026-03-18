package org.example.parking_management_system.service;

import org.example.parking_management_system.dto.request.ReservationCreateDTO;
import org.example.parking_management_system.dto.request.ReservationQueryDTO;
import org.example.parking_management_system.dto.response.ReservationCreateResponseDTO;
import org.example.parking_management_system.dto.response.ReservationDetailsDTO;
import org.example.parking_management_system.dto.response.ReservationListDTO;

public interface ReservationService {
    
    /**
     * 创建预约
     * @param userId 用户ID
     * @param createDTO 创建预约请求DTO
     * @return 创建预约响应DTO
     */
    ReservationCreateResponseDTO createReservation(Long userId, ReservationCreateDTO createDTO);
    
    /**
     * 取消预约
     * @param userId 用户ID
     * @param reservationId 预约ID
     * @return 操作结果
     */
    boolean cancelReservation(Long userId, Long reservationId);
    
    /**
     * 获取我的预约列表
     * @param userId 用户ID
     * @param queryDTO 查询条件DTO
     * @return 预约列表响应DTO
     */
    ReservationListDTO getMyReservations(Long userId, ReservationQueryDTO queryDTO);
    
    /**
     * 获取预约详情
     * @param userId 用户ID
     * @param reservationId 预约ID
     * @return 预约详情响应DTO
     */
    ReservationDetailsDTO getReservationDetails(Long userId, Long reservationId);
}
