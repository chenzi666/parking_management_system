package org.example.parking_management_system.service.admin.impl;

import org.example.parking_management_system.dto.admin.request.AddFeeRuleDTO;
import org.example.parking_management_system.dto.admin.request.UpdateFeeRuleDTO;
import org.example.parking_management_system.dto.admin.response.AddFeeRuleDetailsDTO;
import org.example.parking_management_system.dto.admin.response.QueryFeeRuleDTO;
import org.example.parking_management_system.dto.admin.response.UpdateFeeRuleDetailsDTO;
import org.example.parking_management_system.entity.FeeRule;
import org.example.parking_management_system.entity.ParkingLot;
import org.example.parking_management_system.exception.BusinessException;
import org.example.parking_management_system.mapper.admin.AdminFeeRuleMapper;
import org.example.parking_management_system.mapper.admin.AdminParkingLotMapper;
import org.example.parking_management_system.service.admin.AdminFeeRuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdminFeeRuleServiceImpl implements AdminFeeRuleService {
    @Autowired
    private AdminFeeRuleMapper adminFeeRuleMapper;
    @Autowired
    private AdminParkingLotMapper adminParkingLotMapper;


    private String ruleTypeText(String ruleType){
        return switch (ruleType) {
            case "hourly" -> "按时收费";
            case "monthly" -> "按月收费";
            default -> "未知";
        };
    }
    private String statusText(Integer status){
        return switch (status){
            case 0 -> "禁用";
            case 1 -> "启用";
            default -> "未知";
        };
    }
    @Override
    public List<QueryFeeRuleDTO> findByAll(Integer parkingLotId, String ruleType, Integer status) {

        List<FeeRule> feeRules = adminFeeRuleMapper.findByAll(parkingLotId,ruleType,status);
        List<Long> id = feeRules.stream().map(FeeRule::getParkingLotId).toList();

       Map<Long,String> parkingLotNameMap = id.isEmpty()? new HashMap<>():
               adminParkingLotMapper.findByIdAll(id).stream()
                       .collect(Collectors.toMap(ParkingLot::getId,ParkingLot::getName));

        List<QueryFeeRuleDTO> queryFeeRuleDTOS = new ArrayList<>();
        for (FeeRule feeRule:feeRules){
            QueryFeeRuleDTO dto = new QueryFeeRuleDTO();
            BeanUtils.copyProperties(feeRule,dto);

            String parkingLotName = parkingLotNameMap.get(feeRule.getParkingLotId());
            dto.setParkingLotName(parkingLotName != null? parkingLotName:"未知");

            dto.setRuleTypeText(ruleTypeText(feeRule.getRuleType()));
            dto.setStatusText(statusText(feeRule.getStatus()));

            queryFeeRuleDTOS.add(dto);
        }
        return queryFeeRuleDTOS;
    }

    @Override
    public AddFeeRuleDetailsDTO insert(AddFeeRuleDTO addFeeRuleDTO) {
        if (addFeeRuleDTO == null){
            throw new BusinessException("数据不能为空");
        }
        Long id = adminFeeRuleMapper.insert(addFeeRuleDTO);
        FeeRule feeRule = adminFeeRuleMapper.findById(id);
        AddFeeRuleDetailsDTO addFeeRuleDetailsDTO = new AddFeeRuleDetailsDTO();
        BeanUtils.copyProperties(feeRule,addFeeRuleDetailsDTO);
        return addFeeRuleDetailsDTO;
    }

    @Override
    public UpdateFeeRuleDetailsDTO updateById(Long id, UpdateFeeRuleDTO updateFeeRuleDTO) {
        if (id == null || updateFeeRuleDTO == null){
            throw new BusinessException("id或数据不能为空");
        }
        adminFeeRuleMapper.updateById(id,updateFeeRuleDTO);
        FeeRule feeRule = adminFeeRuleMapper.findById(id);
        UpdateFeeRuleDetailsDTO updateFeeRuleDetailsDTO = new UpdateFeeRuleDetailsDTO();
        BeanUtils.copyProperties(feeRule,updateFeeRuleDetailsDTO);
        return updateFeeRuleDetailsDTO;
    }

    @Override
    public void defaultById(Long id) {
        FeeRule feeRule = adminFeeRuleMapper.findById(id);
        if (feeRule == null){
            throw new BusinessException("无此规则");
        }
        if (feeRule.getIsDefault() == 1){
            throw new BusinessException("规则已为默认");
        }
        adminFeeRuleMapper.defaultById(id);
    }

    @Override
    public void deleteById(Long id) {
        FeeRule feeRule = adminFeeRuleMapper.findById(id);
        if (feeRule == null){
            throw new BusinessException("无此规则");
        }
        adminFeeRuleMapper.deleteById(id);
    }

}
