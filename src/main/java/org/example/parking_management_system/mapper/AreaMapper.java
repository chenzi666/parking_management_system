package org.example.parking_management_system.mapper;

import org.example.parking_management_system.dto.response.AreaDetailsDTO;
import org.example.parking_management_system.entity.Area;

import java.util.List;

public interface AreaMapper {
    List<AreaDetailsDTO> findByParkingLotId(Long id);

    List<Area> findByIds(List<Long> areaId);
}
