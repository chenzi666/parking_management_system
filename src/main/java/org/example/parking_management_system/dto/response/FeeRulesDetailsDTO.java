package org.example.parking_management_system.dto.response;

import java.math.BigDecimal;

public class FeeRulesDetailsDTO {
    private Long id;
    private String name;
    private String ruleType;
    private BigDecimal firstHourFee;
    private BigDecimal additionalHourFee;
    private BigDecimal dailyMaxFee;

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
}
