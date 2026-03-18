package org.example.parking_management_system.exception;

/**
 * 数据未找到异常类
 */
public class ResourceNotFoundException extends RuntimeException {
    private Integer code;
    private String message;

    public ResourceNotFoundException(String message) {
        super(message);
        this.code = 404;
        this.message = message;
    }

    public ResourceNotFoundException(String resource, Long id) {
        super(String.format("%s未找到，ID: %d", resource, id));
        this.code = 404;
        this.message = String.format("%s未找到，ID: %d", resource, id);
    }

    public ResourceNotFoundException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
