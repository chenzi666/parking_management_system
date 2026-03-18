package org.example.parking_management_system.mapper.admin;

import org.apache.ibatis.annotations.Param;
import org.example.parking_management_system.dto.admin.request.AddParkingLotDTO;
import org.example.parking_management_system.dto.admin.request.QueryParkingLotDTO;
import org.example.parking_management_system.dto.admin.request.UpdateParkingLotDTO;
import org.example.parking_management_system.entity.ParkingLot;

import java.util.List;

public interface AdminParkingLotMapper {
    List<ParkingLot> findByIdAll(List<Long> parkingLotIds);

    List<ParkingLot> findByAll(@Param("queryDTO") QueryParkingLotDTO queryDTO, @Param("offset") int offset);

    Long countAll(@Param("queryDTO") QueryParkingLotDTO queryDTO);

    int insert(AddParkingLotDTO addParkingLotDTO);

    ParkingLot findById(Long id);

    Long updateById(Long id, UpdateParkingLotDTO updateParkingLotDTO);

    void deleteById(Long id);
}
