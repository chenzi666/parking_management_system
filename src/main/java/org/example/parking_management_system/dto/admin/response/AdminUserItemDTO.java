package org.example.parking_management_system.dto.admin.response;

import java.util.ArrayList;
import java.util.List;

public class AdminUserItemDTO {
    private List<AdminUserDTO> list;
    private Integer total;
    private Integer page;
    private Integer pageSize;
    public AdminUserItemDTO(){
        this.list = new ArrayList<>();
    }
    public List<AdminUserDTO> getList() {
        return list;
    }

    public void setList(List<AdminUserDTO> list) {
        this.list = list;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
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
