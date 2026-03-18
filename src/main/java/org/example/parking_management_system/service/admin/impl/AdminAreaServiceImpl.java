package org.example.parking_management_system.service.admin.impl;

import org.example.parking_management_system.dto.admin.request.AddAreaDTO;
import org.example.parking_management_system.dto.admin.request.UpdateAreaDTO;
import org.example.parking_management_system.dto.admin.response.AddAreaDetailsDTO;
import org.example.parking_management_system.dto.admin.response.QueryAreaDTO;
import org.example.parking_management_system.dto.admin.response.UpdateAreaDetailsDTO;
import org.example.parking_management_system.entity.Area;
import org.example.parking_management_system.exception.BusinessException;
import org.example.parking_management_system.mapper.admin.AdminAreaMapper;
import org.example.parking_management_system.service.admin.AdminAreaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminAreaServiceImpl implements AdminAreaService {
    @Autowired
    private AdminAreaMapper adminAreaMapper;

    @Override
    public List<QueryAreaDTO> findByParkingLotIdAll(Long parkingLotId) {
        if (parkingLotId == null){
            throw new BusinessException("id不能为空");
        }
        List<Area> areaList = adminAreaMapper.findByParkingLotIdAll(parkingLotId);
        List<QueryAreaDTO> queryAreaDTOS = new ArrayList<>();

        for (Area area:areaList){
            QueryAreaDTO dto = new QueryAreaDTO();
            BeanUtils.copyProperties(area,dto);
            queryAreaDTOS.add(dto);
        }

        return queryAreaDTOS;
    }

    @Override
    public AddAreaDetailsDTO insert(Long parkingLotId, AddAreaDTO addAreaDTO) {
        if (parkingLotId == null || addAreaDTO == null){
            throw new BusinessException("id或数据不能为空");
        }
       Long id = adminAreaMapper.insert(parkingLotId,addAreaDTO);
       Area area = adminAreaMapper.findById(id);
       AddAreaDetailsDTO addAreaDetailsDTO = new AddAreaDetailsDTO();
       BeanUtils.copyProperties(area,addAreaDetailsDTO);
        return addAreaDetailsDTO;
    }

    @Override
    public UpdateAreaDetailsDTO updateById(Long id, UpdateAreaDTO areaDTO) {
        if (id == null || areaDTO == null) {
            throw new BusinessException("id或数据不能为空");
        }
        adminAreaMapper.updateById(id, areaDTO);
        Area area = adminAreaMapper.findById(id);
        UpdateAreaDetailsDTO updateAreaDetailsDTO = new UpdateAreaDetailsDTO();
        BeanUtils.copyProperties(area, updateAreaDetailsDTO);
        return updateAreaDetailsDTO;
    }

    @Override
    public void deleteById(Long id) {
        if (id == null){
            throw new BusinessException("id不能为空");
        }
        if (adminAreaMapper.findById(id) == null){
            throw new BusinessException("当前id数据不存在");
        }
        adminAreaMapper.deleteById(id);
    }

}
