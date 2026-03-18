package org.example.parking_management_system.mapper;

import org.example.parking_management_system.dto.request.ParkingLotQueryDTO;
import org.example.parking_management_system.entity.ParkingLot;

import java.util.List;

public interface ParkingLotMapper {
    List<ParkingLot> findAll(ParkingLotQueryDTO queryDTO);

    Long count(ParkingLotQueryDTO queryDTO);

    ParkingLot findById(Long id);

    ParkingLot parkingStatusById(Long id);
}
