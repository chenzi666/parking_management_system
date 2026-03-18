package org.example.parking_management_system.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ParkingRecord {
    private Long id;
    private Long vehicleId;
    private Long parkingLotId;
    private Long parkingSpaceId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private Integer parkingDuration;
    private BigDecimal totalFee;
    private Integer paymentStatus;
    private Integer isMonthly;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public ParkingRecord() {}

    public ParkingRecord(Long vehicleId, Long parkingLotId, Long parkingSpaceId, LocalDateTime entryTime, 
                         LocalDateTime exitTime, Integer parkingDuration, BigDecimal totalFee, 
                         Integer paymentStatus, Integer isMonthly) {
        this.vehicleId = vehicleId;
        this.parkingLotId = parkingLotId;
        this.parkingSpaceId = parkingSpaceId;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.parkingDuration = parkingDuration;
        this.totalFee = totalFee;
        this.paymentStatus = paymentStatus;
        this.isMonthly = isMonthly;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }

    public Integer getParkingDuration() {
        return parkingDuration;
    }

    public void setParkingDuration(Integer parkingDuration) {
        this.parkingDuration = parkingDuration;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
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
        return "ParkingRecord{" +
                "id=" + id +
                ", vehicleId=" + vehicleId +
                ", parkingLotId=" + parkingLotId +
                ", parkingSpaceId=" + parkingSpaceId +
                ", entryTime=" + entryTime +
                ", exitTime=" + exitTime +
                ", parkingDuration=" + parkingDuration +
                ", totalFee=" + totalFee +
                ", paymentStatus=" + paymentStatus +
                ", isMonthly=" + isMonthly +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}