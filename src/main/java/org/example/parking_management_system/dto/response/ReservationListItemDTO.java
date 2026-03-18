package org.example.parking_management_system.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 预约列表项响应DTO
 */
public class ReservationListItemDTO {
    
    private Long id;
    
    private String parkingLotName;
    
    private String parkingSpaceNumber;
    
    private String plateNumber;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String startTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endTime;
    
    private Integer status;
    
    private String statusText;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    public String getParkingSpaceNumber() {
        return parkingSpaceNumber;
    }

    public void setParkingSpaceNumber(String parkingSpaceNumber) {
        this.parkingSpaceNumber = parkingSpaceNumber;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }
}