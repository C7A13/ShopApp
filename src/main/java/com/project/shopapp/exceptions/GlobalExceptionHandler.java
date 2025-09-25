package com.project.shopapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;
// import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Xử lý custom AppException
    // @ExceptionHandler(AppException.class)
    // public ResponseEntity<Map<String, Object>> handleAppException(AppException
    // ex) {
    // Map<String, Object> response = new HashMap<>();
    // response.put("status", ex.getStatus().value());
    // response.put("error", ex.getStatus().getReasonPhrase());
    // response.put("message", ex.getMessage());
    // return ResponseEntity.status(ex.getStatus()).body(response);
    // }

    // Xử lý DataNotFoundException
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleDataNotFound(DataNotFoundException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.NOT_FOUND.value());
        response.put("error", "Not Found");
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    // Xử lý InvalidParamException
    @ExceptionHandler(InvalidParamException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidParam(InvalidParamException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Invalid Parameter");
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    // Xử lý lỗi validate @Valid (Validation)
    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // public ResponseEntity<Map<String, Object>>
    // handleValidationErrors(MethodArgumentNotValidException ex) {
    // Map<String, Object> response = new HashMap<>();
    // response.put("status", HttpStatus.BAD_REQUEST.value());
    // response.put("error", "Validation Error");
    // response.put("message", ex.getBindingResult().getFieldErrors()
    // .stream()
    // .map(err -> err.getField() + ": " + err.getDefaultMessage())
    // .collect(Collectors.toList()));
    // return ResponseEntity.badRequest().body(response);
    // }

    // Xử lý tất cả Exception còn lại
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneralException(Exception ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.put("error", "Internal Server Error");
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}
