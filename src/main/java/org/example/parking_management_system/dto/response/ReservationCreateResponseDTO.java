package org.example.parking_management_system.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 创建预约响应DTO
 */
public class ReservationCreateResponseDTO {
    
    private Long id;
    
    private Long parkingSpaceId;
    
    private String parkingSpaceNumber;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String startTime;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endTime;
    
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParkingSpaceId() {
        return parkingSpaceId;
    }

    public void setParkingSpaceId(Long parkingSpaceId) {
        this.parkingSpaceId = parkingSpaceId;
    }

    public String getParkingSpaceNumber() {
        return parkingSpaceNumber;
    }

    public void setParkingSpaceNumber(String parkingSpaceNumber) {
        this.parkingSpaceNumber = parkingSpaceNumber;
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
}