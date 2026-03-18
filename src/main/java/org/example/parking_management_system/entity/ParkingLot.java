package org.example.parking_management_system.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ParkingLot {
    private Long id;
    private String name;
    private String address;
    private Integer totalSpaces;
    private Integer availableSpaces;
    private String contactPhone;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String description;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public ParkingLot() {}

    public ParkingLot(String name, String address, Integer totalSpaces, Integer availableSpaces, 
                      String contactPhone, BigDecimal latitude, BigDecimal longitude, String description, Integer status) {
        this.name = name;
        this.address = address;
        this.totalSpaces = totalSpaces;
        this.availableSpaces = availableSpaces;
        this.contactPhone = contactPhone;
        this.latitude = latitude;
        this.longitude = longitude;
        this.description = description;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getTotalSpaces() {
        return totalSpaces;
    }

    public void setTotalSpaces(Integer totalSpaces) {
        this.totalSpaces = totalSpaces;
    }

    public Integer getAvailableSpaces() {
        return availableSpaces;
    }

    public void setAvailableSpaces(Integer availableSpaces) {
        this.availableSpaces = availableSpaces;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "ParkingLot{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", totalSpaces=" + totalSpaces +
                ", availableSpaces=" + availableSpaces +
                ", contactPhone='" + contactPhone + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}