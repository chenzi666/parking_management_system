package org.example.parking_management_system.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MonthlyRental {
    private Long id;
    private Long vehicleId;
    private Long parkingLotId;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal fee;
    private Integer paymentStatus;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public MonthlyRental() {}

    public MonthlyRental(Long vehicleId, Long parkingLotId, LocalDateTime startDate, LocalDateTime endDate, 
                         BigDecimal fee, Integer paymentStatus, Integer status) {
        this.vehicleId = vehicleId;
        this.parkingLotId = parkingLotId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.fee = fee;
        this.paymentStatus = paymentStatus;
        this.status = status;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
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
        return "MonthlyRental{" +
                "id=" + id +
                ", vehicleId=" + vehicleId +
                ", parkingLotId=" + parkingLotId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", fee=" + fee +
                ", paymentStatus=" + paymentStatus +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}