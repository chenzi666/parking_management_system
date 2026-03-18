package org.example.parking_management_system.service;

import org.example.parking_management_system.dto.admin.request.UpdateVehicleDTO;
import org.example.parking_management_system.dto.request.VehicleAddDTO;
import org.example.parking_management_system.dto.response.VehicleAddDetailsDTO;
import org.example.parking_management_system.dto.response.VehicleDetailsDTO;
import org.example.parking_management_system.dto.response.VehicleUpdateDetailsDTO;

import java.util.List;

public interface VehicleService {
    VehicleAddDetailsDTO addVehicle(Long userId,VehicleAddDTO vehicleAddDTO);

    void deleteVehicle(Long id);

    VehicleUpdateDetailsDTO updateVehicle(Long userId,Long id, UpdateVehicleDTO updateVehicleDTO);

    List<VehicleDetailsDTO> findByUserId(Long userId);

    VehicleDetailsDTO findById(Long userId, Long id);
}
