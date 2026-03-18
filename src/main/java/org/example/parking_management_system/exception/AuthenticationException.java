package org.example.parking_management_system.exception;

/**
 * 认证异常类 - 用于JWT认证相关异常
 */
public class AuthenticationException extends RuntimeException {
    private Integer code;
    private String message;

    public AuthenticationException(String message) {
        super(message);
        this.code = 401;
        this.message = message;
    }

    public AuthenticationException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
        this.code = 401;
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
