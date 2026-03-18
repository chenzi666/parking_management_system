package org.example.parking_management_system.service.admin.impl;

import org.example.parking_management_system.dto.admin.request.QueryOperationLogDTO;
import org.example.parking_management_system.dto.admin.response.OperationLogListDTO;
import org.example.parking_management_system.dto.admin.response.OperationLogListItemDTO;
import org.example.parking_management_system.entity.AdminUser;
import org.example.parking_management_system.entity.OperationLog;
import org.example.parking_management_system.exception.BusinessException;
import org.example.parking_management_system.mapper.admin.AdminOperationLogMapper;
import org.example.parking_management_system.mapper.admin.AdminUserMapper;
import org.example.parking_management_system.service.admin.AdminOperationLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作日志Service实现类
 */
@Service
public class AdminOperationLogServiceImpl implements AdminOperationLogService {
    
    @Autowired
    private AdminOperationLogMapper adminOperationLogMapper;
    
    @Autowired
    private AdminUserMapper adminUserMapper;
    
    @Override
    public OperationLogListDTO findAll(QueryOperationLogDTO queryDTO) {
        // 验证分页参数
        if (queryDTO.getPage() == null || queryDTO.getPage() < 1) {
            queryDTO.setPage(1);
        }
        if (queryDTO.getPageSize() == null || queryDTO.getPageSize() < 1) {
            queryDTO.setPageSize(10);
        }
        if (queryDTO.getPageSize() > 100) {
            throw new BusinessException("每页条数不能超过100");
        }
        
        // 计算偏移量
        int offset = (queryDTO.getPage() - 1) * queryDTO.getPageSize();
        
        // 查询操作日志列表
        List<OperationLog> logs = adminOperationLogMapper.findAll(queryDTO, offset);
        
        // 查询总数
        Long total = adminOperationLogMapper.countAll(queryDTO);
        
        // 收集管理员ID
        List<Long> adminIds = new ArrayList<>();
        for (OperationLog log : logs) {
            if (log.getAdminId() != null && !adminIds.contains(log.getAdminId())) {
                adminIds.add(log.getAdminId());
            }
        }
        
        // 批量查询管理员信息
        Map<Long, String> adminNameMap = new HashMap<>();
        if (!adminIds.isEmpty()) {
            List<AdminUser> adminUsers = adminUserMapper.findByIds(adminIds);
            for (AdminUser adminUser : adminUsers) {
                adminNameMap.put(adminUser.getId(), adminUser.getRealName());
            }
        }
        
        // 组装返回数据
        List<OperationLogListItemDTO> list = new ArrayList<>();
        for (OperationLog log : logs) {
            OperationLogListItemDTO dto = new OperationLogListItemDTO();
            BeanUtils.copyProperties(log, dto);
            
            // 设置管理员名称
            dto.setAdminName(adminNameMap.getOrDefault(log.getAdminId(), "未知"));
            
            // 设置操作类型文本
            dto.setOperationTypeText(getOperationTypeText(log.getOperationType()));
            
            list.add(dto);
        }
        
        // 组装分页结果
        OperationLogListDTO result = new OperationLogListDTO();
        result.setList(list);
        result.setTotal(total);
        result.setPage(queryDTO.getPage());
        result.setPageSize(queryDTO.getPageSize());
        
        return result;
    }
    
    /**
     * 获取操作类型文本
     */
    private String getOperationTypeText(String operationType) {
        if (operationType == null) return "未知";
        return switch (operationType) {
            case "login" -> "登录";
            case "logout" -> "登出";
            case "add" -> "新增";
            case "update" -> "更新";
            case "delete" -> "删除";
            case "query" -> "查询";
            case "export" -> "导出";
            case "import" -> "导入";
            default -> operationType;
        };
    }
}
