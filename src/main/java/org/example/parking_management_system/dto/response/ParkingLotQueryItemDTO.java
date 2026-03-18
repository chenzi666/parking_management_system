package org.example.parking_management_system.dto.response;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotQueryItemDTO {
    private List<ParkingLotQueryDTO> list;
    private Long total;
    private Integer page;
    private Integer pageSize;

    public List<ParkingLotQueryDTO> getList() {
        if (list == null){
            list = new ArrayList<>();
        }
        return list;
    }

    public void setList(List<ParkingLotQueryDTO> list) {
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
