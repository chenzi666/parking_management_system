package org.example.parking_management_system.dto.admin.response;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotListDTO {
    private List<ParkingLotListItemDTO> list;
    private Long total;
    private Integer page;
    private Integer pageSize;

    // getter（无参数，仅用于获取列表）
    public List<ParkingLotListItemDTO> getList() {
        // 懒初始化：如果列表为null，先初始化避免空指针
        if (list == null) {
            list = new ArrayList<>();
        }
        return list;
    }

    // setter（可选，用于整体设置列表）
    public void setList(List<ParkingLotListItemDTO> list) {
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
