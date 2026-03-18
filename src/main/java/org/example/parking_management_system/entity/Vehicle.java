package org.example.parking_management_system.entity;

import java.time.LocalDateTime;

public class Vehicle {
    private Long id;
    private String plateNumber;
    private Long userId;
    private String vehicleType;
    private String color;
    private Integer isDefault;
    private Integer isSpecial;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public Vehicle() {}

    public Vehicle(String plateNumber, Long userId, String vehicleType, String color, Integer isDefault, Integer isSpecial) {
        this.plateNumber = plateNumber;
        this.userId = userId;
        this.vehicleType = vehicleType;
        this.color = color;
        this.isDefault = isDefault;
        this.isSpecial = isSpecial;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Integer getIsSpecial() {
        return isSpecial;
    }

    public void setIsSpecial(Integer isSpecial) {
        this.isSpecial = isSpecial;
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
        return "Vehicle{" +
                "id=" + id +
                ", plateNumber='" + plateNumber + '\'' +
                ", userId=" + userId +
                ", vehicleType='" + vehicleType + '\'' +
                ", color='" + color + '\'' +
                ", isDefault=" + isDefault +
                ", isSpecial=" + isSpecial +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}