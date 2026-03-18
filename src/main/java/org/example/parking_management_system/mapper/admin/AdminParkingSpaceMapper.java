package org.example.parking_management_system.mapper.admin;

import org.apache.ibatis.annotations.Param;
import org.example.parking_management_system.dto.admin.request.QueryParkingSpaceDTO;
import org.example.parking_management_system.dto.admin.request.UpdateParkingSpaceDTO;
import org.example.parking_management_system.entity.ParkingSpace;

import java.util.List;

public interface AdminParkingSpaceMapper {
    
    int insert(ParkingSpace parkingSpace);
    
    int batchInsert(List<ParkingSpace> parkingSpaces);
    
    int updateById(Long id, UpdateParkingSpaceDTO dto);
    
    int deleteById(Long id);

    List<ParkingSpace> findAll(@Param("queryDTO") QueryParkingSpaceDTO queryDTO, @Param("offset") int offset);

    Long countAll(@Param("queryDTO") QueryParkingSpaceDTO queryDTO);
}
