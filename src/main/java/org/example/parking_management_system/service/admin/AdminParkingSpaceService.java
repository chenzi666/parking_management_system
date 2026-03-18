package org.example.parking_management_system.service.admin;

import org.example.parking_management_system.dto.admin.request.AddParkingSpaceDTO;
import org.example.parking_management_system.dto.admin.request.BatchAddParkingSpaceDTO;
import org.example.parking_management_system.dto.admin.request.QueryParkingSpaceDTO;
import org.example.parking_management_system.dto.admin.request.UpdateParkingSpaceDTO;
import org.example.parking_management_system.dto.admin.response.AddParkingSpaceCountDTO;
import org.example.parking_management_system.dto.admin.response.ParkingSpaceDetailsDTO;
import org.example.parking_management_system.dto.admin.response.ParkingSpaceListDTO;

import java.util.List;

public interface AdminParkingSpaceService {
    
    ParkingSpaceDetailsDTO addParkingSpace(AddParkingSpaceDTO addParkingSpaceDTO);

    AddParkingSpaceCountDTO batchAddParkingSpace(List<BatchAddParkingSpaceDTO> batchAddParkingSpaceDTOS);
    
    void updateParkingSpace(Long id, UpdateParkingSpaceDTO dto);
    
    void delById(Long id);

    ParkingSpaceListDTO findByAll(QueryParkingSpaceDTO queryDTO);
}
