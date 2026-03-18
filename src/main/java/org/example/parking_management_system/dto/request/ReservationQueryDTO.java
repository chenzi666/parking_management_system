package org.example.parking_management_system.dto.request;

/**
 * 预约列表查询请求DTO
 */
public class ReservationQueryDTO {
    
    private Integer status;
    
    private Integer page = 1;
    
    private Integer pageSize = 10;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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