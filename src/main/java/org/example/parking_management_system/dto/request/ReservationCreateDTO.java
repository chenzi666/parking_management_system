package org.example.parking_management_system.dto.request;

import jakarta.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 创建预约请求DTO
 */
public class ReservationCreateDTO {
    
    @NotNull(message = "车辆ID不能为空")
    private Long vehicleId;
    
    @NotNull(message = "停车场ID不能为空")
    private Long parkingLotId;
    
    @NotNull(message = "开始时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String startTime;
    
    @NotNull(message = "结束时间不能为空")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String endTime;

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getParkingLotId() {
        return parkingLotId;
    }

    public void setParkingLotId(Long parkingLotId) {
        this.parkingLotId = parkingLotId;
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
}