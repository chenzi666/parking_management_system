package org.example.parking_management_system.controller.admin;

import org.example.parking_management_system.dto.admin.request.AddFeeRuleDTO;
import org.example.parking_management_system.dto.admin.request.UpdateFeeRuleDTO;
import org.example.parking_management_system.dto.admin.response.AddFeeRuleDetailsDTO;
import org.example.parking_management_system.dto.admin.response.QueryFeeRuleDTO;
import org.example.parking_management_system.dto.admin.response.UpdateFeeRuleDetailsDTO;
import org.example.parking_management_system.service.admin.AdminFeeRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminFeeRuleController {
    @Autowired
    private AdminFeeRuleService adminFeeRuleService;
    @GetMapping("/fee-rule")
    public Map<String,Object> getFeeRuleAll(
            @RequestParam(value = "parkingLotId",required = false) Integer parkingLotId,
            @RequestParam(value = "ruleType",required = false)String ruleType,
            @RequestParam(value = "status",required = false) Integer status){
        Map<String,Object> result = new HashMap<>();
        List<QueryFeeRuleDTO> queryFeeRuleDTOS = adminFeeRuleService.findByAll(parkingLotId,ruleType,status);
      result.put("code",200);
      result.put("message","查询成功");
      result.put("data",queryFeeRuleDTOS);
      return result;
    }
    @PostMapping("/fee-rule")
    public Map<String,Object> addFeeRule(@RequestBody AddFeeRuleDTO addFeeRuleDTO){
        Map<String,Object> result = new HashMap<>();
        AddFeeRuleDetailsDTO addFeeRuleDetailsDTO = adminFeeRuleService.insert(addFeeRuleDTO);
        result.put("code",200);
        result.put("message","收费规则添加成功");
        result.put("data",addFeeRuleDetailsDTO);
        return result;
    }
    @PutMapping("/fee-rule/{id}")
    public Map<String,Object> updateFeeRule(@PathVariable Long id,@RequestBody UpdateFeeRuleDTO updateFeeRuleDTO){
        Map<String,Object> result = new HashMap<>();
        UpdateFeeRuleDetailsDTO updateFeeRuleDetailsDTO = adminFeeRuleService.updateById(id,updateFeeRuleDTO);
        result.put("code",200);
        result.put("message","收费更新成功");
        result.put("data",updateFeeRuleDetailsDTO);
        return result;
    }
    @PutMapping("/fee-rule/{id}/default")
    public Map<String,Object> defaultFeeRul(@PathVariable Long id){
        Map<String,Object> result = new HashMap<>();
        adminFeeRuleService.defaultById(id);
        result.put("code",200);
        result.put("message","设置默认成功");
        result.put("data",null);
        return result;
    }
    @DeleteMapping("/fee-rule/{id}")
    public Map<String,Object> deleteFeeRul(@PathVariable Long id){
        Map<String,Object> result = new HashMap<>();
        adminFeeRuleService.deleteById(id);
        result.put("code",200);
        result.put("message","收费规则删除成功");
        result.put("data",null);
        return result;
    }
}
