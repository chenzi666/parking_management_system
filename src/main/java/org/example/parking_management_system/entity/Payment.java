package org.example.parking_management_system.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {
    private Long id;
    private Long userId;
    private Long parkingRecordId;
    private Long monthlyRentalId;
    private BigDecimal amount;
    private String paymentMethod;
    private String transactionId;
    private Integer status;
    private LocalDateTime createdAt;

    // Constructors
    public Payment() {}

    public Payment(Long userId, Long parkingRecordId, Long monthlyRentalId, BigDecimal amount, 
                   String paymentMethod, String transactionId, Integer status) {
        this.userId = userId;
        this.parkingRecordId = parkingRecordId;
        this.monthlyRentalId = monthlyRentalId;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.transactionId = transactionId;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getParkingRecordId() {
        return parkingRecordId;
    }

    public void setParkingRecordId(Long parkingRecordId) {
        this.parkingRecordId = parkingRecordId;
    }

    public Long getMonthlyRentalId() {
        return monthlyRentalId;
    }

    public void setMonthlyRentalId(Long monthlyRentalId) {
        this.monthlyRentalId = monthlyRentalId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", userId=" + userId +
                ", parkingRecordId=" + parkingRecordId +
                ", monthlyRentalId=" + monthlyRentalId +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}