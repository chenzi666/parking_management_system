package org.example.parking_management_system.mapper.admin;

import org.apache.ibatis.annotations.Param;
import org.example.parking_management_system.dto.admin.request.QueryOperationLogDTO;
import org.example.parking_management_system.entity.OperationLog;

import java.util.List;

/**
 * 操作日志Mapper接口
 */
public interface AdminOperationLogMapper {
    
    /**
     * 查询操作日志列表
     */
    List<OperationLog> findAll(@Param("queryDTO") QueryOperationLogDTO queryDTO, @Param("offset") int offset);
    
    /**
     * 统计操作日志总数
     */
    Long countAll(@Param("queryDTO") QueryOperationLogDTO queryDTO);
    
    /**
     * 根据管理员ID删除操作日志
     */
    int deleteByAdminId(Long adminId);
}
