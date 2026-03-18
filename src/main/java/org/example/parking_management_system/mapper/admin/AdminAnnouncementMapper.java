package org.example.parking_management_system.mapper.admin;

import org.apache.ibatis.annotations.Param;
import org.example.parking_management_system.dto.admin.request.QueryAnnouncementDTO;
import org.example.parking_management_system.dto.admin.request.UpdateAnnouncementDTO;
import org.example.parking_management_system.entity.Announcement;

import java.util.List;

/**
 * 公告管理Mapper接口
 */
public interface AdminAnnouncementMapper {
    
    /**
     * 查询公告列表
     */
    List<Announcement> findAll(@Param("queryDTO") QueryAnnouncementDTO queryDTO, @Param("offset") int offset);
    
    /**
     * 统计公告总数
     */
    Long countAll(@Param("queryDTO") QueryAnnouncementDTO queryDTO);
    
    /**
     * 根据ID查询公告
     */
    Announcement findById(Long id);
    
    /**
     * 新增公告
     */
    int insert(Announcement announcement);
    
    /**
     * 更新公告
     */
    int updateById(@Param("id") Long id, @Param("dto") UpdateAnnouncementDTO dto);
    
    /**
     * 删除公告
     */
    int deleteById(Long id);
}
