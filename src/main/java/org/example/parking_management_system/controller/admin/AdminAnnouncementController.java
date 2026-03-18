package org.example.parking_management_system.controller.admin;

import org.example.parking_management_system.dto.admin.request.AddAnnouncementDTO;
import org.example.parking_management_system.dto.admin.request.QueryAnnouncementDTO;
import org.example.parking_management_system.dto.admin.request.UpdateAnnouncementDTO;
import org.example.parking_management_system.dto.admin.response.AddAnnouncementDetailsDTO;
import org.example.parking_management_system.dto.admin.response.AnnouncementListDTO;
import org.example.parking_management_system.dto.admin.response.UpdateAnnouncementDetailsDTO;
import org.example.parking_management_system.service.admin.AdminAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 公告管理Controller
 */
@RestController
@RequestMapping("/api/v1/admin/")
public class AdminAnnouncementController {
    
    @Autowired
    private AdminAnnouncementService adminAnnouncementService;
    
    /**
     * 获取公告列表
     */
    @GetMapping("/announcement")
    public Map<String, Object> getAnnouncementList(QueryAnnouncementDTO queryDTO) {
        // 设置默认分页参数
        if (queryDTO.getPage() == null || queryDTO.getPage() < 1) {
            queryDTO.setPage(1);
        }
        if (queryDTO.getPageSize() == null || queryDTO.getPageSize() < 1) {
            queryDTO.setPageSize(10);
        }
        
        AnnouncementListDTO resultData = adminAnnouncementService.findAll(queryDTO);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "查询成功");
        result.put("data", resultData);
        return result;
    }
    
    /**
     * 新增公告
     */
    @PostMapping("/announcement")
    public Map<String, Object> addAnnouncement(@RequestBody AddAnnouncementDTO dto) {
        // TODO: 从JWT token或session中获取当前管理员ID，这里暂时写死为1
        Long adminId = 1L;
        
        AddAnnouncementDetailsDTO resultData = adminAnnouncementService.insert(dto, adminId);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "公告发布成功");
        result.put("data", resultData);
        return result;
    }
    
    /**
     * 更新公告
     */
    @PutMapping("/announcement/{id}")
    public Map<String, Object> updateAnnouncement(@PathVariable Long id, @RequestBody UpdateAnnouncementDTO dto) {
        UpdateAnnouncementDetailsDTO resultData = adminAnnouncementService.updateById(id, dto);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "公告更新成功");
        result.put("data", resultData);
        return result;
    }
    
    /**
     * 删除公告
     */
    @DeleteMapping("/announcement/{id}")
    public Map<String, Object> deleteAnnouncement(@PathVariable Long id) {
        adminAnnouncementService.deleteById(id);
        Map<String, Object> result = new HashMap<>();
        result.put("code", 200);
        result.put("message", "公告删除成功");
        result.put("data", null);
        return result;
    }
}
