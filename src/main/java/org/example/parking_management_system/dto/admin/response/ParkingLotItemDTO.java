package org.example.parking_management_system.dto.admin.response;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotItemDTO {
    private List<QueryParkingLotDTO> list;
    private Long total;
    private Integer pageNum;
    private Integer pageSize;


    public List<QueryParkingLotDTO> getList() {

        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    // setter（可选，用于整体设置列表）
    public void setList(List<QueryParkingLotDTO> list) {
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
