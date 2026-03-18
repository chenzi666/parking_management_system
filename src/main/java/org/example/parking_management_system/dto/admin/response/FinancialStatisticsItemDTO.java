package org.example.parking_management_system.dto.admin.response;

import java.math.BigDecimal;
import java.util.List;

public class FinancialStatisticsItemDTO {
    private BigDecimal totalAmount;
    private Long totalCount;
    private BigDecimal averageAmount;
    private List<FinancialStatisticsDTO> chartData;

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public BigDecimal getAverageAmount() {
        return averageAmount;
    }

    public void setAverageAmount(BigDecimal averageAmount) {
        this.averageAmount = averageAmount;
    }

    public List<FinancialStatisticsDTO> getChartData() {
        return chartData;
    }

    public void setChartData(List<FinancialStatisticsDTO> chartData) {
        this.chartData = chartData;
    }
}
