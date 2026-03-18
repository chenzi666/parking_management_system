package org.example.parking_management_system.service.admin.impl;

import org.example.parking_management_system.dto.admin.request.AddParkingSpaceDTO;
import org.example.parking_management_system.dto.admin.request.BatchAddParkingSpaceDTO;
import org.example.parking_management_system.dto.admin.request.QueryParkingSpaceDTO;
import org.example.parking_management_system.dto.admin.request.UpdateParkingSpaceDTO;
import org.example.parking_management_system.dto.admin.response.AddParkingSpaceCountDTO;
import org.example.parking_management_system.dto.admin.response.ParkingSpaceDTO;
import org.example.parking_management_system.dto.admin.response.ParkingSpaceDetailsDTO;
import org.example.parking_management_system.dto.admin.response.ParkingSpaceListDTO;
import org.example.parking_management_system.entity.Area;
import org.example.parking_management_system.entity.ParkingLot;
import org.example.parking_management_system.entity.ParkingSpace;
import org.example.parking_management_system.exception.BusinessException;
import org.example.parking_management_system.mapper.admin.AdminAreaMapper;
import org.example.parking_management_system.mapper.admin.AdminParkingLotMapper;
import org.example.parking_management_system.mapper.admin.AdminParkingSpaceMapper;
import org.example.parking_management_system.service.admin.AdminParkingSpaceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class AdminParkingSpaceServiceImpl implements AdminParkingSpaceService {
    
    @Autowired
    private AdminParkingSpaceMapper adminParkingSpaceMapper;
    @Autowired
    private AdminParkingLotMapper adminParkingLotMapper;
    @Autowired
    private AdminAreaMapper adminAreaMapper;
    @Override
    public ParkingSpaceListDTO findByAll(QueryParkingSpaceDTO queryDTO) {
        // 验证分页参数
        if (queryDTO.getPage() == null || queryDTO.getPage() < 1) {
            queryDTO.setPage(1);
        }
        if (queryDTO.getPageSize() == null || queryDTO.getPageSize() < 1) {
            queryDTO.setPageSize(10);
        }
        if (queryDTO.getPageSize() > 100) {
            throw new BusinessException("每页条数不能超过100");
        }
        
        // 计算偏移量
        int offset = (queryDTO.getPage() - 1) * queryDTO.getPageSize();
        
        // 查询车位列表
        List<ParkingSpace> parkingSpaces = adminParkingSpaceMapper.findAll(queryDTO, offset);
        
        // 查询总数
        Long total = adminParkingSpaceMapper.countAll(queryDTO);

        // 收集停车场ID
        List<Long> parkingLotIds = parkingSpaces.stream()
                .map(ParkingSpace::getParkingLotId)
                .filter(Objects::nonNull)
                .toList();

        // 批量查询停车场信息
        Map<Long, String> parkingLotNameMap = new HashMap<>();
        if (!parkingLotIds.isEmpty()) {
            List<ParkingLot> parkingLots = adminParkingLotMapper.findByIdAll(parkingLotIds);
            parkingLotNameMap = parkingLots.stream()
                    .collect(Collectors.toMap(ParkingLot::getId, ParkingLot::getName));
        }

        // 收集区域ID
        List<Long> areaIds = parkingSpaces.stream()
                .map(ParkingSpace::getAreaId)
                .filter(Objects::nonNull)
                .toList();
                
        // 批量查询区域信息
        Map<Long, String> areaNameMap = new HashMap<>();
        if (!areaIds.isEmpty()) {
            List<Area> areaList = adminAreaMapper.findByIdAll(areaIds);
            areaNameMap = areaList.stream()
                    .collect(Collectors.toMap(Area::getId, Area::getName));
        }

        // 组装DTO列表
        List<ParkingSpaceDTO> list = new ArrayList<>();
        for (ParkingSpace space : parkingSpaces) {
            ParkingSpaceDTO dto = new ParkingSpaceDTO();
            BeanUtils.copyProperties(space, dto);

            dto.setParkingLotName(parkingLotNameMap.getOrDefault(dto.getParkingLotId(), "未知"));
            dto.setAreaName(areaNameMap.getOrDefault(dto.getAreaId(), "未知"));
            dto.setStatusText(getStatusText(space.getStatus()));
            dto.setSpaceTypeText(getSpaceTypeText(space.getSpaceType()));

            list.add(dto);
        }
        
        // 组装返回结果
        ParkingSpaceListDTO result = new ParkingSpaceListDTO();
        result.setList(list);
        result.setTotal(total);
        result.setPage(queryDTO.getPage());
        result.setPageSize(queryDTO.getPageSize());
        
        return result;
    }


    @Override
    public ParkingSpaceDetailsDTO addParkingSpace(AddParkingSpaceDTO addParkingSpaceDTO) {
        if (addParkingSpaceDTO == null) {
            throw new BusinessException("车位信息不能为空");
        }
        
        ParkingSpace parkingSpace = new ParkingSpace();
        BeanUtils.copyProperties(addParkingSpaceDTO, parkingSpace);
        parkingSpace.setStatus(0); // 默认空闲
        
        int rows = adminParkingSpaceMapper.insert(parkingSpace);
        if (rows == 0) {
            throw new BusinessException("添加车位失败");
        }
        
        ParkingSpaceDetailsDTO result = new ParkingSpaceDetailsDTO();
        BeanUtils.copyProperties(parkingSpace, result);
        return result;
    }
    
    @Override
    public AddParkingSpaceCountDTO batchAddParkingSpace(List<BatchAddParkingSpaceDTO> batchAddParkingSpaceDTOS) {
        if (batchAddParkingSpaceDTOS == null || batchAddParkingSpaceDTOS.isEmpty()) {
            throw new BusinessException("批量添加数据不能为空");
        }

        List<ParkingSpace> parkingSpaces = new ArrayList<>();
        for (BatchAddParkingSpaceDTO dto : batchAddParkingSpaceDTOS) {

            int startNumber = Integer.parseInt(dto.getStartNumber().substring(1));
            int endNumber = Integer.parseInt(dto.getEndNumber().substring(1));
            String prefix = dto.getStartNumber().substring(0, 1);
            IntStream.rangeClosed(startNumber,endNumber).forEach(i->{
                    ParkingSpace space = new ParkingSpace();
                    space.setSpaceNumber(prefix+i);
                    space.setParkingLotId(dto.getParkingLotId());
                    space.setAreaId(dto.getAreaId());
                    space.setSpaceType(dto.getSpaceType());
                    space.setIsMonthly(dto.getIsMonthly());
                    space.setStatus(0);
                    parkingSpaces.add(space);
            });
        }
        
        int rows = adminParkingSpaceMapper.batchInsert(parkingSpaces);
        if (rows == 0) {
            throw new BusinessException("批量添加失败");
        }
        AddParkingSpaceCountDTO addParkingSpaceCountDTO = new AddParkingSpaceCountDTO();
        addParkingSpaceCountDTO.setCount(rows);
        return addParkingSpaceCountDTO;
    }
    
    @Override
    public void updateParkingSpace(Long id, UpdateParkingSpaceDTO dto) {
        if (id == null || dto == null) {
            throw new BusinessException("参数不能为空");
        }
        
        int rows = adminParkingSpaceMapper.updateById(id, dto);
        if (rows == 0) {
            throw new BusinessException("更新失败，车位不存在");
        }
    }
    
    @Override
    public void delById(Long id) {
        if (id == null) {
            throw new BusinessException("车位ID不能为空");
        }
        
        int rows = adminParkingSpaceMapper.deleteById(id);
        if (rows == 0) {
            throw new BusinessException("删除失败，车位不存在");
        }
    }
    
    private String getStatusText(Integer status) {
        if (status == null) return "未知";
        return switch (status) {
            case 0 -> "空闲";
            case 1 -> "占用";
            case 2 -> "维护中";
            default -> "未知";
        };
    }
    
    private String getSpaceTypeText(String spaceType) {
        if (spaceType == null) return "未知";
        return switch (spaceType) {
            case "normal" -> "普通车位";
            case "vip" -> "VIP车位";
            case "disabled" -> "无障碍车位";
            case "charging" -> "充电车位";
            default -> spaceType;
        };
    }
}
