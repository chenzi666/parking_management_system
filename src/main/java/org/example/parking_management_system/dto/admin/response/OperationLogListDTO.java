package org.example.parking_management_system.dto.admin.response;

import java.util.List;

/**
 * 操作日志列表分页DTO
 */
public class OperationLogListDTO {
    private List<OperationLogListItemDTO> list;
    private Long total;
    private Integer page;
    private Integer pageSize;

    public List<OperationLogListItemDTO> getList() {
        return list;
    }

    public void setList(List<OperationLogListItemDTO> list) {
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
