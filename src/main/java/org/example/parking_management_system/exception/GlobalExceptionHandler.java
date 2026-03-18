package org.example.parking_management_system.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理器
 * 统一处理系统中的各类异常，返回标准化的错误响应
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessException(BusinessException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", e.getCode());
        result.put("message", e.getMessage());
        result.put("data", null);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    /**
     * 处理认证异常
     */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, Object>> handleAuthenticationException(AuthenticationException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", e.getCode());
        result.put("message", e.getMessage());
        result.put("data", null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

    /**
     * 处理资源未找到异常
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", e.getCode());
        result.put("message", e.getMessage());
        result.put("data", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
    }

    /**
     * 处理JWT过期异常
     */
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<Map<String, Object>> handleExpiredJwtException(ExpiredJwtException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 401);
        result.put("message", "Token已过期，请重新登录");
        result.put("data", null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

    /**
     * 处理JWT签名异常
     */
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<Map<String, Object>> handleSignatureException(SignatureException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 401);
        result.put("message", "Token签名无效");
        result.put("data", null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

    /**
     * 处理JWT格式异常
     */
    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<Map<String, Object>> handleMalformedJwtException(MalformedJwtException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 401);
        result.put("message", "Token格式错误");
        result.put("data", null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

    /**
     * 处理其他JWT异常
     */
    @ExceptionHandler(JwtException.class)
    public ResponseEntity<Map<String, Object>> handleJwtException(JwtException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 401);
        result.put("message", "Token验证失败: " + e.getMessage());
        result.put("data", null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
    }

    /**
     * 处理缺少请求体异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 400);
        result.put("message", "请求体不能为空或格式错误");
        result.put("data", null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    /**
     * 处理缺少请求头异常
     */
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<Map<String, Object>> handleMissingRequestHeaderException(MissingRequestHeaderException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 400);
        result.put("message", "缺少请求头: " + e.getHeaderName());
        result.put("data", null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    /**
     * 处理参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, Object> result = new HashMap<>();
        Map<String, String> errors = new HashMap<>();
        
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        
        result.put("code", 400);
        result.put("message", "参数校验失败");
        result.put("data", errors);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    /**
     * 处理参数类型不匹配异常
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 400);
        result.put("message", String.format("参数类型错误: %s 应该为 %s 类型", 
            e.getName(), e.getRequiredType().getSimpleName()));
        result.put("data", null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    /**
     * 处理数据库访问异常
     */
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<Map<String, Object>> handleDataAccessException(DataAccessException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 500);
        result.put("message", "数据库操作失败");
        result.put("data", null);
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }

    /**
     * 处理SQL异常
     */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Map<String, Object>> handleSQLException(SQLException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 500);
        result.put("message", "SQL执行异常");
        result.put("data", null);
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }

    /**
     * 处理空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Map<String, Object>> handleNullPointerException(NullPointerException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 500);
        result.put("message", "系统内部错误：空指针异常");
        result.put("data", null);
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }

    /**
     * 处理非法参数异常
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 400);
        result.put("message", "参数错误: " + e.getMessage());
        result.put("data", null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    /**
     * 处理运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 500);
        result.put("message", e.getMessage() != null ? e.getMessage() : "系统运行时异常");
        result.put("data", null);
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }

    /**
     * 处理所有未捕获的异常
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleException(Exception e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 500);
        result.put("message", "系统内部错误");
        result.put("data", null);
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
    }
}
