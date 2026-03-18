package org.example.parking_management_system.dto.admin.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 支付记录响应DTO
 * 
 * 用于返回支付记录的详细信息
 */
public class PaymentRecordDTO {
    /**
     * 支付记录ID
     */
    private Long id;
    
    /**
     * 停车场名称
     */
    private String parkingLotName;
    
    /**
     * 车牌号
     */
    private String plateNumber;
    
    /**
     * 支付金额
     */
    private BigDecimal amount;
    
    /**
     * 支付方式（wechat/alipay/balance）
     */
    private String paymentMethod;
    
    /**
     * 支付方式文本
     */
    private String paymentMethodText;
    
    /**
     * 交易流水号
     */
    private String transactionId;
    
    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
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

    public String getPaymentMethodText() {
        return paymentMethodText;
    }

    public void setPaymentMethodText(String paymentMethodText) {
        this.paymentMethodText = paymentMethodText;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
