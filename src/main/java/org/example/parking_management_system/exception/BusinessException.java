package org.example.parking_management_system.exception;

/**
 * 业务异常类
 */
public class BusinessException extends RuntimeException {
    private Integer code;
    private String message;

    public BusinessException(String message) {
        super(message);
        this.code = 400;
        this.message = message;
    }

    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
        this.code = 400;
        this.message = message;
    }

    public BusinessException(Integer code, String message, Throwable cause) {
        super(message, cause);
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
