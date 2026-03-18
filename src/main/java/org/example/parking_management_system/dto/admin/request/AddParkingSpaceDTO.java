package org.example.parking_management_system.dto.admin.request;

public class AddParkingSpaceDTO {
    private Long parkingLotId;
    private Long areaId;
    private String spaceNumber;
    private String spaceType;
    private Integer isMonthly;

    public Long getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Long parkingLotId) {
        this.parkingLotId = parkingLotId;
    }

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

    public Integer getIsMonthly() {
        return isMonthly;
    }

    public void setIsMonthly(Integer isMonthly) {
        this.isMonthly = isMonthly;
    }
}
