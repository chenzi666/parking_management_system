package org.example.parking_management_system.mapper;

import org.example.parking_management_system.dto.admin.request.UpdateVehicleDTO;
import org.example.parking_management_system.dto.request.VehicleAddDTO;
import org.example.parking_management_system.entity.Vehicle;

import java.util.List;

public interface VehicleMapper {
    Long addVehicle(Long userId, VehicleAddDTO vehicleAddDTO);

    Vehicle findById(Long id);

    Vehicle findByPlateNumber(String plateNumber);

    void deleteVehicle(Long id);

    void updateVehicle(Long id, UpdateVehicleDTO updateVehicleDTO);

    List<Vehicle> findByUserId(Long userId);
}
