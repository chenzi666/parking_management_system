package org.example.parking_management_system.dto.admin.response;

import java.math.BigDecimal;
import java.util.List;

/**
 * 运营数据看板响应DTO
 * 
 * 用于展示运营数据概览和统计信息
 */
public class DashboardDTO {
    /**
     * 总收入金额
     */
    private BigDecimal totalRevenue;
    
    /**
     * 总订单数
     */
    private Long totalOrders;
    
    /**
     * 总车辆数
     */
    private Long totalVehicles;
    
    /**
     * 平均占用率
     */
    private BigDecimal averageOccupancyRate;
    
    /**
     * 高峰时段统计
     */
    private List<PeakHourDTO> peakHours;
    
    /**
     * 支付方式分布
     */
    private List<PaymentMethodDistributionDTO> paymentMethodDistribution;

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public Long getTotalOrders() {
        return totalOrders;
    }

    public void setTotalOrders(Long totalOrders) {
        this.totalOrders = totalOrders;
    }

    public Long getTotalVehicles() {
        return totalVehicles;
    }

    public void setTotalVehicles(Long totalVehicles) {
        this.totalVehicles = totalVehicles;
    }

    public BigDecimal getAverageOccupancyRate() {
        return averageOccupancyRate;
    }

    public void setAverageOccupancyRate(BigDecimal averageOccupancyRate) {
        this.averageOccupancyRate = averageOccupancyRate;
    }

    public List<PeakHourDTO> getPeakHours() {
        return peakHours;
    }

    public void setPeakHours(List<PeakHourDTO> peakHours) {
        this.peakHours = peakHours;
    }

    public List<PaymentMethodDistributionDTO> getPaymentMethodDistribution() {
        return paymentMethodDistribution;
    }

    public void setPaymentMethodDistribution(List<PaymentMethodDistributionDTO> paymentMethodDistribution) {
        this.paymentMethodDistribution = paymentMethodDistribution;
    }
    
    /**
     * 高峰时段DTO
     */
    public static class PeakHourDTO {
        /**
         * 小时（0-23）
         */
        private String hour;
        
        /**
         * 该时段的订单数量
         */
        private Long count;

        public String getHour() {
            return hour;
        }

        public void setHour(String hour) {
            this.hour = hour;
        }

        public Long getCount() {
            return count;
        }

        public void setCount(Long count) {
            this.count = count;
        }
    }
    
    /**
     * 支付方式分布DTO
     */
    public static class PaymentMethodDistributionDTO {
        /**
         * 支付方式
         */
        private String method;
        
        /**
         * 订单数量
         */
        private Long count;
        
        /**
         * 百分比
         */
        private Integer percentage;

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public Long getCount() {
            return count;
        }

        public void setCount(Long count) {
            this.count = count;
        }

        public Integer getPercentage() {
            return percentage;
        }

        public void setPercentage(Integer percentage) {
            this.percentage = percentage;
        }
    }
}
