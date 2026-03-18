package org.example.parking_management_system.entity;

import java.time.LocalDateTime;

public class ParkingSpace {
    private Long id;
    private Long parkingLotId;
    private Long areaId;
    private String spaceNumber;
    private String spaceType;
    private Integer status;
    private Integer isMonthly;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public ParkingSpace() {}

    public ParkingSpace(Long parkingLotId, Long areaId, String spaceNumber, String spaceType, 
                        Integer status, Integer isMonthly) {
        this.parkingLotId = parkingLotId;
        this.areaId = areaId;
        this.spaceNumber = spaceNumber;
        this.spaceType = spaceType;
        this.status = status;
        this.isMonthly = isMonthly;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ParkingSpace{" +
                "id=" + id +
                ", parkingLotId=" + parkingLotId +
                ", areaId=" + areaId +
                ", spaceNumber='" + spaceNumber + '\'' +
                ", spaceType='" + spaceType + '\'' +
                ", status=" + status +
                ", isMonthly=" + isMonthly +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}