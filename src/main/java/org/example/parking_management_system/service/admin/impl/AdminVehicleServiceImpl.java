package org.example.parking_management_system.service.admin.impl;

import org.example.parking_management_system.dto.admin.request.ImportVehicleDTO;
import org.example.parking_management_system.dto.admin.request.QueryVehicleDTO;
import org.example.parking_management_system.dto.admin.request.UpdateVehicleDTO;
import org.example.parking_management_system.dto.admin.response.VehicleDetailsDTO;
import org.example.parking_management_system.dto.admin.response.VehicleListItemDTO;
import org.example.parking_management_system.entity.User;
import org.example.parking_management_system.entity.Vehicle;
import org.example.parking_management_system.exception.BusinessException;
import org.example.parking_management_system.mapper.admin.AdminUserMapper;
import org.example.parking_management_system.mapper.admin.AdminVehicleMapper;
import org.example.parking_management_system.mapper.admin.ManageUsersMapper;
import org.example.parking_management_system.service.admin.AdminVehicleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AdminVehicleServiceImpl implements AdminVehicleService {
    @Autowired
    private AdminVehicleMapper adminVehicleMapper;
    @Autowired
    private ManageUsersMapper manageUsersMapper;
    
    @Override
    public Map<String, Object> vehicleAll(QueryVehicleDTO queryDTO) {
        // 计算分页偏移量
        int offset = (queryDTO.getPage() - 1) * queryDTO.getPageSize();
        
        // 查询车辆列表
        List<Vehicle> vehicles = adminVehicleMapper.vehicleAll(queryDTO, offset);
        
        // 查询总数
        int total = adminVehicleMapper.countVehicles(queryDTO);
        
        List<VehicleListItemDTO> list = new ArrayList<>();
        
        if (!vehicles.isEmpty()) {
            // 收集所有用户ID
            Set<Long> userIds = new HashSet<>();
            for (Vehicle vehicle : vehicles) {
                if (vehicle.getUserId() != null) {
                    userIds.add(vehicle.getUserId());
                }
            }

            // 批量查询用户信息
            Map<Long, User> userMap = new HashMap<>();
            if (!userIds.isEmpty()) {
                List<User> users = manageUsersMapper.findByIds(new ArrayList<>(userIds));
                for (User user : users) {
                    userMap.put(user.getId(), user);
                }
            }

            // 组装DTO
            for (Vehicle vehicle : vehicles) {
                VehicleListItemDTO dto = new VehicleListItemDTO();
                BeanUtils.copyProperties(vehicle, dto);
                
                Long userId = vehicle.getUserId();
                User user = userMap.get(userId);
                if (user != null) {
                    dto.setUserName(user.getNickname());
                    dto.setPhone(user.getPhone());
                } else {
                    dto.setUserName("未知用户");
                    dto.setPhone("未知手机号");
                }
                list.add(dto);
            }
        }

        // 组装返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("list", list);
        result.put("total", total);
        result.put("page", queryDTO.getPage());
        result.put("pageSize", queryDTO.getPageSize());
        
        return result;
    }

    @Override
    public VehicleDetailsDTO findById(Long id) {
        if (id == null) {
            throw new BusinessException("车辆ID不能为空");
        }
        
        Vehicle vehicle = adminVehicleMapper.findById(id);
        if (vehicle == null) {
            throw new BusinessException("车辆不存在");
        }
        
        VehicleDetailsDTO vehicleDetailsDTO = new VehicleDetailsDTO();
        BeanUtils.copyProperties(vehicle, vehicleDetailsDTO);
        
        // 查询用户信息
        if (vehicle.getUserId() != null) {
            User user = manageUsersMapper.findById(vehicle.getUserId());
            if (user != null) {
                vehicleDetailsDTO.setUserName(user.getNickname());
                vehicleDetailsDTO.setPhone(user.getPhone());
            }
        }
        
        return vehicleDetailsDTO;
    }

    @Override
    public VehicleDetailsDTO updateById(Long id, UpdateVehicleDTO dto) {
        if (id == null || dto == null){
            throw new BusinessException("参数不能为空");
        }
        
        int rows = adminVehicleMapper.updateById(id, dto);
        if (rows == 0) {
            throw new BusinessException("更新失败，车辆不存在");
        }
        
        Vehicle vehicle = adminVehicleMapper.findById(id);
        VehicleDetailsDTO vehicleDetailsDTO = new VehicleDetailsDTO();
        BeanUtils.copyProperties(vehicle, vehicleDetailsDTO);
        
        return vehicleDetailsDTO;
    }

    @Override
    public List<ImportVehicleDTO> batchImport(List<ImportVehicleDTO> importVehicleDTOList) {
        if (importVehicleDTOList == null || importVehicleDTOList.isEmpty()){
            throw new BusinessException("导入数据不能为空");
        }
        
        // 设置默认值
        for (ImportVehicleDTO dto : importVehicleDTOList){
            if (dto.getIsDefault() == null){
                dto.setIsDefault(0);
            }
            if (dto.getIsSpecial() == null){
                dto.setIsSpecial(0);
            }
        }
        
        int rows = adminVehicleMapper.batchImport(importVehicleDTOList);
        if (rows > 0) {
            return importVehicleDTOList;
        }
        
        throw new BusinessException("批量导入失败");
    }
}