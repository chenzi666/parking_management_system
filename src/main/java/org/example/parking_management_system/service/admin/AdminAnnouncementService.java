package org.example.parking_management_system.service.admin;

import org.example.parking_management_system.dto.admin.request.AddAnnouncementDTO;
import org.example.parking_management_system.dto.admin.request.QueryAnnouncementDTO;
import org.example.parking_management_system.dto.admin.request.UpdateAnnouncementDTO;
import org.example.parking_management_system.dto.admin.response.AddAnnouncementDetailsDTO;
import org.example.parking_management_system.dto.admin.response.AnnouncementListDTO;
import org.example.parking_management_system.dto.admin.response.UpdateAnnouncementDetailsDTO;

/**
 * 公告管理Service接口
 */
public interface AdminAnnouncementService {
    
    /**
     * 获取公告列表
     */
    AnnouncementListDTO findAll(QueryAnnouncementDTO queryDTO);
    
    /**
     * 新增公告
     */
    AddAnnouncementDetailsDTO insert(AddAnnouncementDTO dto, Long adminId);
    
    /**
     * 更新公告
     */
    UpdateAnnouncementDetailsDTO updateById(Long id, UpdateAnnouncementDTO dto);
    
    /**
     * 删除公告
     */
    void deleteById(Long id);
}
