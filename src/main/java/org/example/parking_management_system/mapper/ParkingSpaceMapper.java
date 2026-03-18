package org.example.parking_management_system.mapper;

import org.example.parking_management_system.dto.response.AreaDetailsDTO;
import org.example.parking_management_system.entity.ParkingSpace;

import java.util.List;

public interface ParkingSpaceMapper {
    List<ParkingSpace> findByParkingLotId(Long id);

    List<AreaDetailsDTO> totalSpacesCount(List<Long> areaId);

    List<AreaDetailsDTO> availableSpacesCount(List<Long> areaId);

    Integer countOccupiedSpaces(Long parkingLotId);

    Integer countReservedSpaces(Long parkingLotId);
}
