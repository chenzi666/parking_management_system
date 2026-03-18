package org.example.parking_management_system.service.admin;

import org.example.parking_management_system.dto.admin.request.ImportVehicleDTO;
import org.example.parking_management_system.dto.admin.request.QueryVehicleDTO;
import org.example.parking_management_system.dto.admin.request.UpdateVehicleDTO;
import org.example.parking_management_system.dto.admin.response.VehicleDetailsDTO;

import java.util.List;
import java.util.Map;

public interface AdminVehicleService {
    Map<String, Object> vehicleAll(QueryVehicleDTO queryDTO);

    VehicleDetailsDTO findById(Long id);

    VehicleDetailsDTO updateById(Long id, UpdateVehicleDTO dto);

    List<ImportVehicleDTO> batchImport(List<ImportVehicleDTO> importVehicleDTOList);
}
