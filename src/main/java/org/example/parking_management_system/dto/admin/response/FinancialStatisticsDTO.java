package org.example.parking_management_system.dto.admin.response;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FinancialStatisticsDTO {
    private LocalDate date;
    private BigDecimal amount;
    private Long count;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
