package org.example.parking_management_system.mapper.admin;

import org.example.parking_management_system.dto.admin.request.AddAreaDTO;
import org.example.parking_management_system.dto.admin.request.UpdateAreaDTO;
import org.example.parking_management_system.entity.Area;

import java.util.List;

public interface AdminAreaMapper {
    List<Area> findByIdAll(List<Long> areaId);


    List<Area> findByParkingLotIdAll(Long parkingLotId);

    Long insert(Long parkingLotId, AddAreaDTO addAreaDTO);

    Area findById(Long id);

    void updateById(Long id, UpdateAreaDTO areaDTO);

    void deleteById(Long id);
}
