package com.convention_store.handler;

import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.convention_store")
public class GlobalControllerAdvice {
    
    private static final Logger logger = LoggerFactory.getLogger(GlobalControllerAdvice.class);
    
    // 1. JSON 파싱 오류 (요청 본문 형식 오류) -> 400 Bad Request
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadable(
        HttpMessageNotReadableException ex
    ) {
        logger.warn("Bad Request: Invalid or malformed request body. {}", ex.getMessage());
        // 사용자에게 노출할 메시지 정의
        String errorMessage = "요청 본문의 형식이 올바르지 않습니다. JSON 형식을 확인해주세요.";
        return new ResponseEntity<>(
            errorMessage, // 클라이언트에게는 일반적인 메시지 전달
            HttpStatus.BAD_REQUEST // 400
        );
    }
    
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(Exception ex) {
        logger.error(ex.getMessage());
        return new ResponseEntity<>(
            ex.getMessage(),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
    
    // 특정 예외에 대한 핸들러 추가
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgument(
        IllegalArgumentException ex
    ) {
        return new ResponseEntity<>(
            ex.getMessage(),
            HttpStatus.BAD_REQUEST // 400 응답
        );
    }
    
    // 더 많은 특정 예외 핸들러를 추가할 수 있습니다
    
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<Object> handleIllegalState(IllegalStateException ex) {
        return new ResponseEntity<>(
            ex.getMessage(),
            HttpStatus.BAD_REQUEST // 400 응답
        );
    }
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleNoSuchElement(
        EntityNotFoundException ex
    ) {
        return new ResponseEntity<>(
            ex.getMessage(),
            HttpStatus.NOT_FOUND
        );
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException e
    ) {
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new ResponseEntity<>(
            message,
            HttpStatus.BAD_REQUEST
        );
    }
}