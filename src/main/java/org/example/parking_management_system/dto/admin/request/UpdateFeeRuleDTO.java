package org.example.parking_management_system.dto.admin.request;

import java.math.BigDecimal;

public class UpdateFeeRuleDTO {
    private Long id;
    private BigDecimal firstHourFee;
    private BigDecimal additionalHourFee;
    private BigDecimal dailyMaxFee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
