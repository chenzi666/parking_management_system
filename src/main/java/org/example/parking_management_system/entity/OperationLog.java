package org.example.parking_management_system.entity;

import java.time.LocalDateTime;

public class OperationLog {
    private Long id;
    private Long adminId;
    private String operationType;
    private String operationContent;
    private String ipAddress;
    private LocalDateTime createdAt;

    // Constructors
    public OperationLog() {}

    public OperationLog(Long adminId, String operationType, String operationContent, String ipAddress, LocalDateTime createdAt) {
        this.adminId = adminId;
        this.operationType = operationType;
        this.operationContent = operationContent;
        this.ipAddress = ipAddress;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAdminId() {
        return adminId;
    }

    public void setAdminId(Long adminId) {
        this.adminId = adminId;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationContent() {
        return operationContent;
    }

    public void setOperationContent(String operationContent) {
        this.operationContent = operationContent;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "OperationLog{" +
                "id=" + id +
                ", adminId=" + adminId +
                ", operationType='" + operationType + '\'' +
                ", operationContent='" + operationContent + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}