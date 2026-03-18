package org.example.parking_management_system.dto.admin.response;

import java.time.LocalDateTime;

/**
 * 操作日志列表项DTO
 */
public class OperationLogListItemDTO {
    private Long id;
    private String adminName;
    private String operationType;
    private String operationTypeText;
    private String operationContent;
    private String ipAddress;
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationTypeText() {
        return operationTypeText;
    }

    public void setOperationTypeText(String operationTypeText) {
        this.operationTypeText = operationTypeText;
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
}
