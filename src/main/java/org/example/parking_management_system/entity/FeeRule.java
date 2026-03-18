package org.example.parking_management_system.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class FeeRule {
    private Long id;
    private Long parkingLotId;
    private String name;
    private String ruleType;
    private BigDecimal firstHourFee;
    private BigDecimal additionalHourFee;
    private BigDecimal dailyMaxFee;
    private BigDecimal monthlyFee;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer isDefault;
    private Integer status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // Constructors
    public FeeRule() {}

    public FeeRule(Long parkingLotId, String name, String ruleType, BigDecimal firstHourFee, 
                   BigDecimal additionalHourFee, BigDecimal dailyMaxFee, BigDecimal monthlyFee, 
                   LocalTime startTime, LocalTime endTime, Integer isDefault, Integer status) {
        this.parkingLotId = parkingLotId;
        this.name = name;
        this.ruleType = ruleType;
        this.firstHourFee = firstHourFee;
        this.additionalHourFee = additionalHourFee;
        this.dailyMaxFee = dailyMaxFee;
        this.monthlyFee = monthlyFee;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isDefault = isDefault;
        this.status = status;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public BigDecimal getFirstHourFee() {
        return firstHourFee;
    }

    public void setFirstHourFee(BigDecimal firstHourFee) {
        this.firstHourFee = firstHourFee;
    }

    public BigDecimal getAdditionalHourFee() {
        return additionalHourFee;
    }

    public void setAdditionalHourFee(BigDecimal additionalHourFee) {
        this.additionalHourFee = additionalHourFee;
    }

    public BigDecimal getDailyMaxFee() {
        return dailyMaxFee;
    }

    public void setDailyMaxFee(BigDecimal dailyMaxFee) {
        this.dailyMaxFee = dailyMaxFee;
    }

    public BigDecimal getMonthlyFee() {
        return monthlyFee;
    }

    public void setMonthlyFee(BigDecimal monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
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
        return "FeeRule{" +
                "id=" + id +
                ", parkingLotId=" + parkingLotId +
                ", name='" + name + '\'' +
                ", ruleType='" + ruleType + '\'' +
                ", firstHourFee=" + firstHourFee +
                ", additionalHourFee=" + additionalHourFee +
                ", dailyMaxFee=" + dailyMaxFee +
                ", monthlyFee=" + monthlyFee +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", isDefault=" + isDefault +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}