package org.example.parking_management_system.dto.admin.request;

public class UpdateParkingSpaceDTO {
    private Long areaId;
    private String spaceNumber;
    private String spaceType;
    private Integer status;
    private Integer isMonthly;

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getSpaceNumber() {
        return spaceNumber;
    }

    public void setSpaceNumber(String spaceNumber) {
        this.spaceNumber = spaceNumber;
    }

    public String getSpaceType() {
        return spaceType;
    }

    public void setSpaceType(String spaceType) {
        this.spaceType = spaceType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsMonthly() {
        return isMonthly;
    }

    public void setIsMonthly(Integer isMonthly) {
        this.isMonthly = isMonthly;
    }
}
