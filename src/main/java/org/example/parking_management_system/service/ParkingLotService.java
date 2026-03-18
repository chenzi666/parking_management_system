package org.example.parking_management_system.service;

import org.example.parking_management_system.dto.request.ParkingLotQueryDTO;
import org.example.parking_management_system.dto.response.ParkingLotDetailsDTO;
import org.example.parking_management_system.dto.response.ParkingLotQueryItemDTO;
import org.example.parking_management_system.dto.response.ParkingStatusItemDTO;

public interface ParkingLotService {
    ParkingLotQueryItemDTO findAll(ParkingLotQueryDTO queryDTO);

    ParkingLotDetailsDTO findById(Long id);

    ParkingStatusItemDTO parkingStatusById(Long id);
}
