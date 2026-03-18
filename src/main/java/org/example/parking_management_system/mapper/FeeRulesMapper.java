package org.example.parking_management_system.mapper;

import org.example.parking_management_system.entity.FeeRule;

import java.util.List;

public interface FeeRulesMapper {
    List<FeeRule> findById(Long id);
}
