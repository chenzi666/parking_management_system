package org.example.parking_management_system.service.admin;

import org.example.parking_management_system.dto.admin.request.AddParkingLotDTO;
import org.example.parking_management_system.dto.admin.request.QueryParkingLotDTO;
import org.example.parking_management_system.dto.admin.request.UpdateParkingLotDTO;
import org.example.parking_management_system.dto.admin.response.AddParkingLotDetailsDTO;
import org.example.parking_management_system.dto.admin.response.ParkingLotListDTO;
import org.example.parking_management_system.dto.admin.response.UpdateParkingLotDetailsDTO;

public interface AdminParkingLotService {

    ParkingLotListDTO findByAll(QueryParkingLotDTO queryDTO);

    AddParkingLotDetailsDTO insert(AddParkingLotDTO addParkingLotDTO);

    UpdateParkingLotDetailsDTO updateById(Long id, UpdateParkingLotDTO updateParkingLotDTO);

    void deleteById(Long id);
}
