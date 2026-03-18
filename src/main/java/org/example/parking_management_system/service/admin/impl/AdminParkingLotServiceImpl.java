package org.example.parking_management_system.service.admin.impl;

import org.example.parking_management_system.dto.admin.request.AddParkingLotDTO;
import org.example.parking_management_system.dto.admin.request.QueryParkingLotDTO;
import org.example.parking_management_system.dto.admin.request.UpdateParkingLotDTO;
import org.example.parking_management_system.dto.admin.response.AddParkingLotDetailsDTO;
import org.example.parking_management_system.dto.admin.response.ParkingLotListDTO;
import org.example.parking_management_system.dto.admin.response.ParkingLotListItemDTO;
import org.example.parking_management_system.dto.admin.response.UpdateParkingLotDetailsDTO;
import org.example.parking_management_system.entity.ParkingLot;
import org.example.parking_management_system.exception.BusinessException;
import org.example.parking_management_system.mapper.admin.AdminParkingLotMapper;
import org.example.parking_management_system.service.admin.AdminParkingLotService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminParkingLotServiceImpl implements AdminParkingLotService {
    @Autowired
    private AdminParkingLotMapper adminParkingLotMapper;


    @Override
    public ParkingLotListDTO findByAll(QueryParkingLotDTO queryDTO) {
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
        
        // 查询总数
        Long total = adminParkingLotMapper.countAll(queryDTO);
        
        // 查询停车场列表
        List<ParkingLot> parkingLots = adminParkingLotMapper.findByAll(queryDTO, offset);
        
        // 组装返回数据
        List<ParkingLotListItemDTO> list = new ArrayList<>();
        for (ParkingLot parkingLot : parkingLots) {
            ParkingLotListItemDTO dto = new ParkingLotListItemDTO();
            BeanUtils.copyProperties(parkingLot, dto);
            
            // 设置状态文本
            dto.setStatusText(getStatusText(parkingLot.getStatus()));
            
            list.add(dto);
        }
        
        // 组装分页结果
        ParkingLotListDTO result = new ParkingLotListDTO();
        result.setList(list);
        result.setTotal(total);
        result.setPage(queryDTO.getPage());
        result.setPageSize(queryDTO.getPageSize());
        
        return result;
    }
    
    private String getStatusText(Integer status) {
        if (status == null) return "未知";
        return switch (status) {
            case 0 -> "关闭";
            case 1 -> "开放";
            default -> "未知";
        };
    }

    @Override
    public AddParkingLotDetailsDTO insert(AddParkingLotDTO addParkingLotDTO) {
        if(addParkingLotDTO == null){
            throw new BusinessException("数据为空");
        }
        Long id = (long) adminParkingLotMapper.insert(addParkingLotDTO);
        ParkingLot parkingLot =  adminParkingLotMapper.findById(id);
        AddParkingLotDetailsDTO addParkingLotDetailsDTO = new AddParkingLotDetailsDTO();
        BeanUtils.copyProperties(parkingLot,addParkingLotDetailsDTO);
        return addParkingLotDetailsDTO;
    }

    @Override
    public UpdateParkingLotDetailsDTO updateById(Long id, UpdateParkingLotDTO updateParkingLotDTO) {
        if (id == null || updateParkingLotDTO == null){
            throw new BusinessException("数据或id为空");
        }
        ParkingLot parkingLot = adminParkingLotMapper.findById(id);
        if (parkingLot == null){
            throw new BusinessException("当前id数据不存在");
        }
      adminParkingLotMapper.updateById(id,updateParkingLotDTO);
        ParkingLot updateparkingLot = adminParkingLotMapper.findById(id);
      UpdateParkingLotDetailsDTO updateParkingLotDetailsDTO = new UpdateParkingLotDetailsDTO();
      BeanUtils.copyProperties(updateparkingLot,updateParkingLotDetailsDTO);
        return updateParkingLotDetailsDTO;
    }

    @Override
    public void deleteById(Long id) {
        if (id == null){
            throw new BusinessException("id不能为空");
        }
      ParkingLot parkingLot =  adminParkingLotMapper.findById(id);
        if (parkingLot == null){
            throw new BusinessException("当前id数据不存在");
        }
        adminParkingLotMapper.deleteById(id);
    }
}
