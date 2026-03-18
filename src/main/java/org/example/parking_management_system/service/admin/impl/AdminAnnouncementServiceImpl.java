package org.example.parking_management_system.service.admin.impl;

import org.example.parking_management_system.dto.admin.request.AddAnnouncementDTO;
import org.example.parking_management_system.dto.admin.request.QueryAnnouncementDTO;
import org.example.parking_management_system.dto.admin.request.UpdateAnnouncementDTO;
import org.example.parking_management_system.dto.admin.response.AddAnnouncementDetailsDTO;
import org.example.parking_management_system.dto.admin.response.AnnouncementListDTO;
import org.example.parking_management_system.dto.admin.response.AnnouncementListItemDTO;
import org.example.parking_management_system.dto.admin.response.UpdateAnnouncementDetailsDTO;
import org.example.parking_management_system.entity.AdminUser;
import org.example.parking_management_system.entity.Announcement;
import org.example.parking_management_system.exception.BusinessException;
import org.example.parking_management_system.mapper.admin.AdminAnnouncementMapper;
import org.example.parking_management_system.mapper.admin.AdminUserMapper;
import org.example.parking_management_system.service.admin.AdminAnnouncementService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公告管理Service实现类
 */
@Service
public class AdminAnnouncementServiceImpl implements AdminAnnouncementService {
    
    @Autowired
    private AdminAnnouncementMapper adminAnnouncementMapper;
    
    @Autowired
    private AdminUserMapper adminUserMapper;
    
    @Override
    public AnnouncementListDTO findAll(QueryAnnouncementDTO queryDTO) {
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
        
        // 查询公告列表
        List<Announcement> announcements = adminAnnouncementMapper.findAll(queryDTO, offset);
        
        // 查询总数
        Long total = adminAnnouncementMapper.countAll(queryDTO);
        
        // 收集管理员ID
        List<Long> adminIds = new ArrayList<>();
        for (Announcement announcement : announcements) {
            if (announcement.getAdminId() != null && !adminIds.contains(announcement.getAdminId())) {
                adminIds.add(announcement.getAdminId());
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
        List<AnnouncementListItemDTO> list = new ArrayList<>();
        for (Announcement announcement : announcements) {
            AnnouncementListItemDTO dto = new AnnouncementListItemDTO();
            BeanUtils.copyProperties(announcement, dto);
            
            // 设置管理员名称
            dto.setAdminName(adminNameMap.getOrDefault(announcement.getAdminId(), "未知"));
            
            // 设置状态文本
            dto.setStatusText(getStatusText(announcement.getStatus()));
            
            list.add(dto);
        }
        
        // 组装分页结果
        AnnouncementListDTO result = new AnnouncementListDTO();
        result.setList(list);
        result.setTotal(total);
        result.setPage(queryDTO.getPage());
        result.setPageSize(queryDTO.getPageSize());
        
        return result;
    }
    
    @Override
    public AddAnnouncementDetailsDTO insert(AddAnnouncementDTO dto, Long adminId) {
        if (dto == null) {
            throw new BusinessException("公告信息不能为空");
        }
        if (dto.getTitle() == null || dto.getTitle().trim().isEmpty()) {
            throw new BusinessException("公告标题不能为空");
        }
        if (dto.getContent() == null || dto.getContent().trim().isEmpty()) {
            throw new BusinessException("公告内容不能为空");
        }
        
        // 组装实体
        Announcement announcement = new Announcement();
        BeanUtils.copyProperties(dto, announcement);
        announcement.setAdminId(adminId);
        
        // 设置默认值
        if (announcement.getIsTop() == null) {
            announcement.setIsTop(0);
        }
        if (announcement.getStatus() == null) {
            announcement.setStatus(1);
        }
        
        // 插入数据库
        int rows = adminAnnouncementMapper.insert(announcement);
        if (rows == 0) {
            throw new BusinessException("添加公告失败");
        }
        
        // 组装返回结果
        AddAnnouncementDetailsDTO result = new AddAnnouncementDetailsDTO();
        result.setId(announcement.getId());
        result.setTitle(announcement.getTitle());
        result.setIsTop(announcement.getIsTop());
        result.setStatus(announcement.getStatus());
        
        return result;
    }
    
    @Override
    public UpdateAnnouncementDetailsDTO updateById(Long id, UpdateAnnouncementDTO dto) {
        if (id == null || dto == null) {
            throw new BusinessException("参数不能为空");
        }
        
        // 验证公告是否存在
        Announcement announcement = adminAnnouncementMapper.findById(id);
        if (announcement == null) {
            throw new BusinessException("公告不存在");
        }
        
        // 更新数据
        int rows = adminAnnouncementMapper.updateById(id, dto);
        if (rows == 0) {
            throw new BusinessException("更新失败");
        }
        
        // 查询更新后的数据
        Announcement updated = adminAnnouncementMapper.findById(id);
        
        // 组装返回结果
        UpdateAnnouncementDetailsDTO result = new UpdateAnnouncementDetailsDTO();
        result.setId(updated.getId());
        result.setTitle(updated.getTitle());
        result.setIsTop(updated.getIsTop());
        result.setStatus(updated.getStatus());
        
        return result;
    }
    
    @Override
    public void deleteById(Long id) {
        if (id == null) {
            throw new BusinessException("公告ID不能为空");
        }
        
        // 验证公告是否存在
        Announcement announcement = adminAnnouncementMapper.findById(id);
        if (announcement == null) {
            throw new BusinessException("公告不存在");
        }
        
        // 删除数据
        int rows = adminAnnouncementMapper.deleteById(id);
        if (rows == 0) {
            throw new BusinessException("删除失败");
        }
    }
    
    /**
     * 获取状态文本
     */
    private String getStatusText(Integer status) {
        if (status == null) return "未知";
        return switch (status) {
            case 0 -> "草稿";
            case 1 -> "发布";
            default -> "未知";
        };
    }
}
