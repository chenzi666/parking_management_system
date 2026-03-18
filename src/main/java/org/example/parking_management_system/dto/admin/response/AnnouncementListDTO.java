package org.example.parking_management_system.dto.admin.response;

import java.util.List;

/**
 * 公告列表分页DTO
 */
public class AnnouncementListDTO {
    private List<AnnouncementListItemDTO> list;
    private Long total;
    private Integer page;
    private Integer pageSize;

    public List<AnnouncementListItemDTO> getList() {
        return list;
    }

    public void setList(List<AnnouncementListItemDTO> list) {
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
