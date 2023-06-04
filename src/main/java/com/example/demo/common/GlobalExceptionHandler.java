package com.example.demo.common;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<ObjectError> errors = bindingResult.getAllErrors();
        // 에러 메시지 추출 및 처리
        StringBuilder errorMessage = new StringBuilder();
        for (ObjectError error : errors) {
            errorMessage.append(error.getDefaultMessage()).append("\n");
        }
        return ResponseEntity.badRequest().body(errorMessage.toString());
    }
}
