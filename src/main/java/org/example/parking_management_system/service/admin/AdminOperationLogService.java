package org.example.parking_management_system.service.admin;

import org.example.parking_management_system.dto.admin.request.QueryOperationLogDTO;
import org.example.parking_management_system.dto.admin.response.OperationLogListDTO;

/**
 * 操作日志Service接口
 */
public interface AdminOperationLogService {
    
    /**
     * 获取操作日志列表
     */
    OperationLogListDTO findAll(QueryOperationLogDTO queryDTO);
}
