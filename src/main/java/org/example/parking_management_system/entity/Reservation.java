package org.example.parking_management_system.entity;

import java.time.LocalDateTime;

public class Reservation {
    private Long id;
    private Long userId;
    private Long vehicleId;
    private Long parkingLotId;
    private Long parkingSpaceId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public Reservation() {}

    public Reservation(Long userId, Long vehicleId, Long parkingLotId, Long parkingSpaceId, 
                       LocalDateTime startTime, LocalDateTime endTime, Integer status) {
        this.userId = userId;
        this.vehicleId = vehicleId;
        this.parkingLotId = parkingLotId;
        this.parkingSpaceId = parkingSpaceId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

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

    public Long getParkingSpaceId() {
        return parkingSpaceId;
    }

    public void setParkingSpaceId(Long parkingSpaceId) {
        this.parkingSpaceId = parkingSpaceId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        return "Reservation{" +
                "id=" + id +
                ", userId=" + userId +
                ", vehicleId=" + vehicleId +
                ", parkingLotId=" + parkingLotId +
                ", parkingSpaceId=" + parkingSpaceId +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}