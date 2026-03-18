package org.example.parking_management_system.service.admin;

import org.example.parking_management_system.dto.admin.request.AddAreaDTO;
import org.example.parking_management_system.dto.admin.request.UpdateAreaDTO;
import org.example.parking_management_system.dto.admin.response.AddAreaDetailsDTO;
import org.example.parking_management_system.dto.admin.response.QueryAreaDTO;
import org.example.parking_management_system.dto.admin.response.UpdateAreaDetailsDTO;

import java.util.List;

public interface AdminAreaService {

    List<QueryAreaDTO> findByParkingLotIdAll(Long parkingLotId);


    AddAreaDetailsDTO insert(Long parkingLotId, AddAreaDTO addAreaDTO);

    UpdateAreaDetailsDTO updateById(Long id, UpdateAreaDTO areaDTO);

    void deleteById(Long id);
}
