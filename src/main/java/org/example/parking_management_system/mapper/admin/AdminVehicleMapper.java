package org.example.parking_management_system.mapper.admin;

import org.apache.ibatis.annotations.Param;
import org.example.parking_management_system.dto.admin.request.ImportVehicleDTO;
import org.example.parking_management_system.dto.admin.request.QueryVehicleDTO;
import org.example.parking_management_system.dto.admin.request.UpdateVehicleDTO;
import org.example.parking_management_system.entity.Vehicle;

import java.util.List;

public interface AdminVehicleMapper {
    List<Vehicle> vehicleAll(@Param("queryDTO") QueryVehicleDTO queryDTO, @Param("offset") int offset);
    
    int countVehicles(@Param("queryDTO") QueryVehicleDTO queryDTO);

    Vehicle findById(Long id);

    int updateById(Long id, UpdateVehicleDTO dto);

    int batchImport(List<ImportVehicleDTO> importVehicleDTOList);
}