package org.example.parking_management_system.mapper.admin;

import org.example.parking_management_system.dto.admin.request.AddFeeRuleDTO;
import org.example.parking_management_system.dto.admin.request.UpdateFeeRuleDTO;
import org.example.parking_management_system.entity.FeeRule;

import java.util.List;

public interface AdminFeeRuleMapper {
    List<FeeRule> findByAll(Integer parkingLotId, String ruleType, Integer status);

    Long insert(AddFeeRuleDTO addFeeRuleDTO);

    FeeRule findById(Long id);

    void updateById(Long id, UpdateFeeRuleDTO updateFeeRuleDTO);

    void defaultById(Long id);

    void deleteById(Long id);
}
