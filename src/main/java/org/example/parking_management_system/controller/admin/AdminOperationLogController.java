package org.example.parking_management_system.controller.admin;

import org.example.parking_management_system.dto.admin.request.QueryOperationLogDTO;
import org.example.parking_management_system.dto.admin.response.OperationLogListDTO;
import org.example.parking_management_system.service.admin.AdminOperationLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 操作日志Controller
 */
@RestController
@RequestMapping("/api/v1/admin/")
public class AdminOperationLogController {
    
    @Autowired
    private AdminOperationLogService adminOperationLogService;
    
    /**
     * 获取操作日志列表
     */
    @GetMapping("/logs")
    public Map<String, Object> getOperationLogs(QueryOperationLogDTO queryDTO) {
        // 设置默认分页参数
        if (queryDTO.getPage() == null || queryDTO.getPage() < 1) {
            queryDTO.setPage(1);
        }
        if (queryDTO.getPageSize() == null || queryDTO.getPageSize() < 1) {
            queryDTO.setPageSize(10);
        }
        
        OperationLogListDTO resultData = adminOperationLogService.findAll(queryDTO);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "查询成功");
        result.put("data", resultData);
        return result;
    }
}
