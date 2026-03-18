package org.example.parking_management_system.service.impl;

import org.example.parking_management_system.dto.admin.request.UpdateVehicleDTO;
import org.example.parking_management_system.dto.request.VehicleAddDTO;
import org.example.parking_management_system.dto.response.VehicleAddDetailsDTO;
import org.example.parking_management_system.dto.response.VehicleDetailsDTO;
import org.example.parking_management_system.dto.response.VehicleUpdateDetailsDTO;
import org.example.parking_management_system.entity.Vehicle;
import org.example.parking_management_system.exception.BusinessException;
import org.example.parking_management_system.mapper.VehicleMapper;
import org.example.parking_management_system.service.VehicleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Autowired
    private VehicleMapper vehicleMapper;

    @Override
    public VehicleAddDetailsDTO addVehicle(Long userId,VehicleAddDTO vehicleAddDTO) {
        if (vehicleAddDTO == null){
            throw new BusinessException("数据不能为空");
        }
        Vehicle plateNumber = vehicleMapper.findByPlateNumber(vehicleAddDTO.getPlateNumber());
        if (plateNumber != null){
            throw new BusinessException("车牌号已存在");
        }
        if (vehicleAddDTO.getIsDefault() == null){
            vehicleAddDTO.setIsDefault(0);
        }
        Long judgment = vehicleMapper.addVehicle(userId,vehicleAddDTO);
        if (judgment == 0){
            throw new BusinessException("添加失败");
        }
        Long vehicleId = vehicleAddDTO.getId();
        Vehicle vehicle = vehicleMapper.findById(vehicleId);
        VehicleAddDetailsDTO vehicleAddDetailsDTO = new VehicleAddDetailsDTO();
        BeanUtils.copyProperties(vehicle,vehicleAddDetailsDTO);
        return vehicleAddDetailsDTO;
    }

    @Override
    public void deleteVehicle(Long id) {
        if (id == null){
            throw new BusinessException("id为空");
        }
        Vehicle vehicleById = vehicleMapper.findById(id);
        if (vehicleById == null){
            throw new BusinessException("当前车辆不存在");
        }
        vehicleMapper.deleteVehicle(id);
    }

    @Override
    public VehicleUpdateDetailsDTO updateVehicle(Long userId,Long id, UpdateVehicleDTO updateVehicleDTO) {
        if (id == null || updateVehicleDTO == null){
            throw new BusinessException("id或数据不能为空");
        }
        List<Vehicle> vehicleByUserId = vehicleMapper.findByUserId(userId);
        if (!vehicleByUserId.isEmpty()){
            throw new BusinessException("当前车辆不存在");
        }
        vehicleMapper.updateVehicle(id,updateVehicleDTO);
        Vehicle vehicle = vehicleMapper.findById(id);
        VehicleUpdateDetailsDTO vehicleUpdateDetailsDTO = new VehicleUpdateDetailsDTO();
        BeanUtils.copyProperties(vehicle,vehicleUpdateDetailsDTO);
        return vehicleUpdateDetailsDTO;
    }

    @Override
    public List<VehicleDetailsDTO> findByUserId(Long userId) {
        List<Vehicle> vehicles = vehicleMapper.findByUserId(userId);
        List<VehicleDetailsDTO> vehicleDetailsDTOS = new ArrayList<>();
        for (Vehicle vehicle:vehicles){
            VehicleDetailsDTO dto = new VehicleDetailsDTO();
            BeanUtils.copyProperties(vehicle,dto);
            vehicleDetailsDTOS.add(dto);
        }
        return vehicleDetailsDTOS;
    }

    @Override
    public VehicleDetailsDTO findById(Long userId, Long id) {
        if (id == null){
            throw new BusinessException("id不能为空");
        }
        Vehicle vehicle = vehicleMapper.findById(id);
        if (vehicle == null || !Objects.equals(vehicle.getUserId(), userId)){
            throw new BusinessException("当前车辆不存在");
        }
        VehicleDetailsDTO vehicleDetailsDTO = new VehicleDetailsDTO();
        BeanUtils.copyProperties(vehicle,vehicleDetailsDTO);
        return vehicleDetailsDTO;
    }
}
