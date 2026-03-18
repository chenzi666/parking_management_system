package org.example.parking_management_system.dto.response;

import java.util.List;

/**
 * 预约列表响应DTO
 */
public class ReservationListDTO {
    
    private List<ReservationListItemDTO> list;
    
    private Long total;
    
    private Integer page;
    
    private Integer pageSize;

    public List<ReservationListItemDTO> getList() {
        return list;
    }

    public void setList(List<ReservationListItemDTO> list) {
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