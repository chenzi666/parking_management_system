package org.example.parking_management_system.service.impl;

import org.example.parking_management_system.dto.response.*;
import org.example.parking_management_system.entity.Area;
import org.example.parking_management_system.entity.FeeRule;
import org.example.parking_management_system.entity.ParkingLot;
import org.example.parking_management_system.entity.ParkingSpace;
import org.example.parking_management_system.exception.BusinessException;
import org.example.parking_management_system.mapper.AreaMapper;
import org.example.parking_management_system.mapper.FeeRulesMapper;
import org.example.parking_management_system.mapper.ParkingLotMapper;
import org.example.parking_management_system.mapper.ParkingSpaceMapper;
import org.example.parking_management_system.service.ParkingLotService;
import org.example.parking_management_system.util.DistanceUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class ParkingLotServiceImpl implements ParkingLotService {
    @Autowired
    private ParkingLotMapper parkingLotMapper;
    @Autowired
    private FeeRulesMapper feeRulesMapper;
    @Autowired
    private ParkingSpaceMapper parkingSpaceMapper;
    @Autowired
    private AreaMapper areaMapper;
    @Override
    public ParkingLotQueryItemDTO findAll(org.example.parking_management_system.dto.request.ParkingLotQueryDTO queryDTO) {
        if (queryDTO.getPage() < 1){
            queryDTO.setPage(1);
        }
        if (queryDTO.getPageSize() < 10){
            queryDTO.setPageSize(10);
        }
        if (queryDTO.getPageSize() > 100){
            queryDTO.setPageSize(100);
        }
        if (queryDTO.getRadius() == null){
            queryDTO.setRadius(5000L);
        }
        Integer page = queryDTO.getPage();
        Integer pageSize = queryDTO.getPageSize();

        queryDTO.setPage ((page -1)*pageSize );
        List<ParkingLot> parkingLots = parkingLotMapper.findAll(queryDTO);
        ParkingLotQueryItemDTO parkingLotQueryItemDTO = new ParkingLotQueryItemDTO();
        long count = 0L;
        for (ParkingLot parkingLot:parkingLots){
            double distance = DistanceUtil.calculateDistanceInKilometers(
                    queryDTO.getLatitude(),queryDTO.getLongitude(),
                    parkingLot.getLatitude(),parkingLot.getLongitude());
            if((distance*1000) <= queryDTO.getRadius()){
                ParkingLotQueryDTO dto = new ParkingLotQueryDTO();
                BeanUtils.copyProperties(parkingLot,dto);
                dto.setDistance(BigDecimal.valueOf(distance));
                parkingLotQueryItemDTO.getList().add(dto);
                parkingLotQueryItemDTO.setTotal(++count);
            }
        }
        parkingLotQueryItemDTO.getList().sort(Comparator.comparing(ParkingLotQueryDTO::getDistance));
        parkingLotQueryItemDTO.setPage(page);
        parkingLotQueryItemDTO.setPageSize(pageSize);
        return parkingLotQueryItemDTO;
    }

    @Override
    public ParkingLotDetailsDTO findById(Long id) {
        if (id == null){
            throw new BusinessException("id不能为空");
        }
        ParkingLot parkingLot = parkingLotMapper.findById(id);
        if (parkingLot == null){
            throw new BusinessException("停车场不存在");
        }
        List<FeeRule> feeRules = feeRulesMapper.findById(id);
        ParkingLotDetailsDTO parkingLotDetailsDTO = new ParkingLotDetailsDTO();
        BeanUtils.copyProperties(parkingLot,parkingLotDetailsDTO);
        for (FeeRule feeRule:feeRules){
            FeeRulesDetailsDTO dto = new FeeRulesDetailsDTO();
            BeanUtils.copyProperties(feeRule,dto);
            parkingLotDetailsDTO.getFeeRules().add(dto);
        }
        return parkingLotDetailsDTO;
    }

    @Override
    public ParkingStatusItemDTO parkingStatusById(Long id) {
        if (id == null){
            throw new BusinessException("id不能为空");
        }
        ParkingLot parkingLot = parkingLotMapper.parkingStatusById(id);
        if (parkingLot == null){
            throw new BusinessException("停车场不存在");
        }
        // 查询该停车场下的所有区域信息
        List<AreaDetailsDTO> areaList = areaMapper.findByParkingLotId(id);
        // 提取区域ID列表，如果区域列表为空则返回空列表
        List<Long> areaIds = areaList.isEmpty()? new ArrayList<>():
                areaList.stream().map(AreaDetailsDTO::getAreaId).toList();

        // 处理停车场没有区域的特殊情况
        if (areaIds.isEmpty()) {
            // 创建返回结果对象
            ParkingStatusItemDTO emptyResult = new ParkingStatusItemDTO();
            // 设置停车场总车位数
            emptyResult.setTotalSpaces(parkingLot.getTotalSpaces());
            // 设置停车场可用车位数
            emptyResult.setAvailableSpaces(parkingLot.getAvailableSpaces());
            // 统计占用车位数（status = 1）
            Integer occupiedSpaces = parkingSpaceMapper.countOccupiedSpaces(id);
            // 统计预约车位数（status = 2）
            Integer reservedSpaces = parkingSpaceMapper.countReservedSpaces(id);
            // 设置占用车位数，如果查询结果为null则默认为0
            emptyResult.setOccupiedSpaces(occupiedSpaces != null ? occupiedSpaces : 0);
            // 设置预约车位数，如果查询结果为null则默认为0
            emptyResult.setReservedSpaces(reservedSpaces != null ? reservedSpaces : 0);
            return emptyResult;
        }

        // 批量查询各区域的总车位数，并转换为Map结构方便后续查找
        // key为区域ID，value为包含该区域总车位数的DTO对象
        Map<Long, AreaDetailsDTO> totalSpacesMap = parkingSpaceMapper.totalSpacesCount(areaIds)
                .stream()
                .collect(Collectors.toMap(AreaDetailsDTO::getAreaId, Function.identity()));

        // 批量查询各区域的可用车位数（status = 0），并转换为Map结构
        // key为区域ID，value为包含该区域可用车位数的DTO对象
        Map<Long, AreaDetailsDTO> availableSpacesMap = parkingSpaceMapper.availableSpacesCount(areaIds)
                .stream()
                .collect(Collectors.toMap(AreaDetailsDTO::getAreaId, Function.identity()));
        
        // 创建最终返回的结果对象
        ParkingStatusItemDTO parkingStatusItemDTO = new ParkingStatusItemDTO();
        
        // 构建各区域的详细信息列表
        // 通过流式处理将Area实体转换为AreaDetailsDTO，并填充统计数据
        List<AreaDetailsDTO> areaDetailsDTOS = areaMapper.findByIds(areaIds)
                .stream()
                .map(area -> {
                    // 创建区域详情DTO对象
                    AreaDetailsDTO dto = new AreaDetailsDTO();
                    // 设置区域ID
                    dto.setAreaId(area.getId());
                    // 设置区域名称
                    dto.setAreaName(area.getName());

                    // 从totalSpacesMap中获取该区域的总车位数并设置
                    // 使用Optional避免空指针异常
                    Optional.ofNullable(totalSpacesMap.get(area.getId()))
                            .ifPresent(t -> dto.setTotalSpaces(t.getTotalSpaces()));
                    // 从availableSpacesMap中获取该区域的可用车位数并设置
                    Optional.ofNullable(availableSpacesMap.get(area.getId()))
                            .ifPresent(a -> dto.setAvailableSpaces(a.getAvailableSpaces()));
                    return dto;
                })
                .collect(Collectors.toList());

        // 设置停车场级别的总车位数
        parkingStatusItemDTO.setTotalSpaces(parkingLot.getTotalSpaces());
        // 设置停车场级别的可用车位数
        parkingStatusItemDTO.setAvailableSpaces(parkingLot.getAvailableSpaces());
        // 统计并设置停车场的占用车位数（status = 1）
        Integer occupiedSpaces = parkingSpaceMapper.countOccupiedSpaces(id);
        // 统计并设置停车场的预约车位数（status = 2）
        Integer reservedSpaces = parkingSpaceMapper.countReservedSpaces(id);
        // 设置占用车位数，防止null值
        parkingStatusItemDTO.setOccupiedSpaces(occupiedSpaces != null ? occupiedSpaces : 0);
        // 设置预约车位数，防止null值
        parkingStatusItemDTO.setReservedSpaces(reservedSpaces != null ? reservedSpaces : 0);
        // 设置按区域划分的车位详情列表
        parkingStatusItemDTO.setSpaceByArea(areaDetailsDTOS);
        return parkingStatusItemDTO;
    }
}
