package org.example.parking_management_system.dto.admin.response;

import java.util.List;

public class ParkingSpaceListDTO {
    private List<ParkingSpaceDTO> list;
    private Long total;
    private Integer page;
    private Integer pageSize;


    public List<ParkingSpaceDTO> getList() {
        return list;
    }

    public void setList(List<ParkingSpaceDTO> list) {
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
