package org.example.parking_management_system.dto.admin.request;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.Date;

public class AddFeeRuleDTO {
    private Long id;
    private Long parkingLotId;
    private String name;
    private String ruleType;
    private BigDecimal firstHourFee;
    private BigDecimal additionalHourFee;
    private BigDecimal dailyMaxFee;
    private LocalTime startTime;
    private LocalTime endTime;
    private Integer isDefault;
    private Integer status;

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
}
