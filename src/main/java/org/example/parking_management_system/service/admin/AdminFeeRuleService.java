package org.example.parking_management_system.service.admin;

import org.example.parking_management_system.dto.admin.request.AddFeeRuleDTO;
import org.example.parking_management_system.dto.admin.request.UpdateFeeRuleDTO;
import org.example.parking_management_system.dto.admin.response.AddFeeRuleDetailsDTO;
import org.example.parking_management_system.dto.admin.response.QueryFeeRuleDTO;
import org.example.parking_management_system.dto.admin.response.UpdateFeeRuleDetailsDTO;

import java.util.List;

public interface AdminFeeRuleService {

    List<QueryFeeRuleDTO> findByAll(Integer parkingLotId, String ruleType, Integer status);

    AddFeeRuleDetailsDTO insert(AddFeeRuleDTO addFeeRuleDTO);

    UpdateFeeRuleDetailsDTO updateById(Long id, UpdateFeeRuleDTO updateFeeRuleDTO);

    void defaultById(Long id);

    void deleteById(Long id);
}
