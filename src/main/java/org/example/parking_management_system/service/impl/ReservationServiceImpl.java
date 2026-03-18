package org.example.parking_management_system.service.impl;

import org.example.parking_management_system.dto.request.ReservationCreateDTO;
import org.example.parking_management_system.dto.request.ReservationQueryDTO;
import org.example.parking_management_system.dto.response.ReservationCreateResponseDTO;
import org.example.parking_management_system.dto.response.ReservationDetailsDTO;
import org.example.parking_management_system.dto.response.ReservationListDTO;
import org.example.parking_management_system.dto.response.ReservationListItemDTO;
import org.example.parking_management_system.entity.ParkingLot;
import org.example.parking_management_system.entity.ParkingSpace;
import org.example.parking_management_system.entity.Reservation;
import org.example.parking_management_system.entity.Vehicle;
import org.example.parking_management_system.exception.BusinessException;
import org.example.parking_management_system.mapper.*;
import org.example.parking_management_system.service.ReservationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationMapper reservationMapper;
    
    @Autowired
    private VehicleMapper vehicleMapper;
    
    @Autowired
    private ParkingLotMapper parkingLotMapper;
    
    @Autowired
    private ParkingSpaceMapper parkingSpaceMapper;
    
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    @Override
    public ReservationCreateResponseDTO createReservation(Long userId, ReservationCreateDTO createDTO) {
        // 验证车辆是否属于当前用户
        Vehicle vehicle = vehicleMapper.findById(createDTO.getVehicleId());
        if (vehicle == null || !vehicle.getUserId().equals(userId)) {
            throw new BusinessException("车辆不存在或不属于当前用户");
        }
        
        // 验证停车场是否存在
        ParkingLot parkingLot = parkingLotMapper.findById(createDTO.getParkingLotId());
        if (parkingLot == null) {
            throw new BusinessException("停车场不存在");
        }
        
        // 查找可用停车位
        List<ParkingSpace> availableSpaces = parkingSpaceMapper.findByParkingLotId(createDTO.getParkingLotId());
        ParkingSpace targetSpace = null;
        
        for (ParkingSpace space : availableSpaces) {
            // 检查停车位是否可用
            if (space.getStatus() != 1) { // 1表示可用
                continue;
            }
            
            // 检查时间段是否有冲突
            long conflictCount = reservationMapper.countConflictingReservations(
                    space.getId(), createDTO.getStartTime(), createDTO.getEndTime(), null);
            
            if (conflictCount == 0) {
                targetSpace = space;
                break;
            }
        }
        
        if (targetSpace == null) {
            throw new BusinessException("指定时间段内没有可用停车位");
        }
        
        // 创建预约记录
        Reservation reservation = new Reservation();
        reservation.setUserId(userId);
        reservation.setVehicleId(createDTO.getVehicleId());
        reservation.setParkingLotId(createDTO.getParkingLotId());
        reservation.setParkingSpaceId(targetSpace.getId());
        reservation.setStartTime(LocalDateTime.parse(createDTO.getStartTime(), dateTimeFormatter));
        reservation.setEndTime(LocalDateTime.parse(createDTO.getEndTime(), dateTimeFormatter));
        reservation.setStatus(1); // 1表示已预约
        reservation.setCreatedAt(LocalDateTime.now());
        reservation.setUpdatedAt(LocalDateTime.now());
        
        int result = reservationMapper.insert(reservation);
        if (result <= 0) {
            throw new BusinessException("创建预约失败");
        }
        
        // 返回创建结果
        ReservationCreateResponseDTO responseDTO = new ReservationCreateResponseDTO();
        responseDTO.setId(reservation.getId());
        responseDTO.setParkingSpaceId(targetSpace.getId());
        responseDTO.setParkingSpaceNumber(targetSpace.getSpaceNumber());
        responseDTO.setStartTime(reservation.getStartTime().format(dateTimeFormatter));
        responseDTO.setEndTime(reservation.getEndTime().format(dateTimeFormatter));
        responseDTO.setStatus(reservation.getStatus());
        
        return responseDTO;
    }
    
    @Override
    public boolean cancelReservation(Long userId, Long reservationId) {
        // 查询预约信息
        Reservation reservation = reservationMapper.selectByUserIdAndId(userId, reservationId);
        if (reservation == null) {
            throw new BusinessException("预约不存在或不属于当前用户");
        }
        
        // 检查预约状态是否可以取消
        if (reservation.getStatus() != 1) { // 只有已预约状态可以取消
            throw new BusinessException("当前预约状态不允许取消");
        }
        
        // 更新预约状态为已取消
        reservation.setUpdatedAt(LocalDateTime.now());
        int result = reservationMapper.updateStatusById(reservationId, 3); // 3表示已取消
        
        return result > 0;
    }
    
    @Override
    public ReservationListDTO getMyReservations(Long userId, ReservationQueryDTO queryDTO) {
        // 计算偏移量
        int offset = (queryDTO.getPage() - 1) * queryDTO.getPageSize();
        
        // 查询预约列表
        List<Reservation> reservations = reservationMapper.selectByUserIdAndStatus(
                userId, queryDTO.getStatus(), offset, queryDTO.getPageSize());
        
        // 查询总数
        long total = reservationMapper.countByUserIdAndStatus(userId, queryDTO.getStatus());
        
        // 转换为响应DTO
        List<ReservationListItemDTO> listItems = new ArrayList<>();
        for (Reservation reservation : reservations) {
            ReservationListItemDTO item = new ReservationListItemDTO();
            item.setId(reservation.getId());
            
            // 获取停车场名称
            ParkingLot parkingLot = parkingLotMapper.findById(reservation.getParkingLotId());
            if (parkingLot != null) {
                item.setParkingLotName(parkingLot.getName());
            }
            
            // 获取停车位编号
            ParkingSpace parkingSpace = parkingSpaceMapper.findByParkingLotId(reservation.getParkingLotId())
                    .stream()
                    .filter(space -> space.getId().equals(reservation.getParkingSpaceId()))
                    .findFirst()
                    .orElse(null);
            if (parkingSpace != null) {
                item.setParkingSpaceNumber(parkingSpace.getSpaceNumber());
            }
            
            // 获取车牌号
            Vehicle vehicle = vehicleMapper.findById(reservation.getVehicleId());
            if (vehicle != null) {
                item.setPlateNumber(vehicle.getPlateNumber());
            }
            
            item.setStartTime(reservation.getStartTime().format(dateTimeFormatter));
            item.setEndTime(reservation.getEndTime().format(dateTimeFormatter));
            item.setStatus(reservation.getStatus());
            item.setStatusText(getStatusText(reservation.getStatus()));
            
            listItems.add(item);
        }
        
        // 构建响应
        ReservationListDTO responseDTO = new ReservationListDTO();
        responseDTO.setList(listItems);
        responseDTO.setTotal(total);
        responseDTO.setPage(queryDTO.getPage());
        responseDTO.setPageSize(queryDTO.getPageSize());
        
        return responseDTO;
    }
    
    @Override
    public ReservationDetailsDTO getReservationDetails(Long userId, Long reservationId) {
        // 查询预约信息
        Reservation reservation = reservationMapper.selectByUserIdAndId(userId, reservationId);
        if (reservation == null) {
            throw new BusinessException("预约不存在或不属于当前用户");
        }
        
        // 构建响应DTO
        ReservationDetailsDTO responseDTO = new ReservationDetailsDTO();
        responseDTO.setId(reservation.getId());
        responseDTO.setParkingLotId(reservation.getParkingLotId());
        responseDTO.setParkingSpaceId(reservation.getParkingSpaceId());
        responseDTO.setVehicleId(reservation.getVehicleId());
        responseDTO.setStartTime(reservation.getStartTime().format(dateTimeFormatter));
        responseDTO.setEndTime(reservation.getEndTime().format(dateTimeFormatter));
        responseDTO.setStatus(reservation.getStatus());
        responseDTO.setStatusText(getStatusText(reservation.getStatus()));
        responseDTO.setCreatedAt(reservation.getCreatedAt().format(dateTimeFormatter));
        
        // 获取停车场名称
        ParkingLot parkingLot = parkingLotMapper.findById(reservation.getParkingLotId());
        if (parkingLot != null) {
            responseDTO.setParkingLotName(parkingLot.getName());
        }
        
        // 获取停车位编号
        ParkingSpace parkingSpace = parkingSpaceMapper.findByParkingLotId(reservation.getParkingLotId())
                .stream()
                .filter(space -> space.getId().equals(reservation.getParkingSpaceId()))
                .findFirst()
                .orElse(null);
        if (parkingSpace != null) {
            responseDTO.setParkingSpaceNumber(parkingSpace.getSpaceNumber());
        }
        
        // 获取车牌号
        Vehicle vehicle = vehicleMapper.findById(reservation.getVehicleId());
        if (vehicle != null) {
            responseDTO.setPlateNumber(vehicle.getPlateNumber());
        }
        
        return responseDTO;
    }
    
    /**
     * 获取预约状态文本
     * @param status 状态码
     * @return 状态文本
     */
    private String getStatusText(Integer status) {
        switch (status) {
            case 1:
                return "已预约";
            case 2:
                return "使用中";
            case 3:
                return "已取消";
            case 4:
                return "已完成";
            default:
                return "未知状态";
        }
    }
}
