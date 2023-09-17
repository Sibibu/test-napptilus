package com.shendelzare.napptilus.adapters.rest.utils;

import com.shendelzare.napptilus.adapters.rest.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiError> handleCustomException(MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ApiError apiError = ApiError.builder()
                .status(status)
                .timestamp(LocalDateTime.now())
                .errors(ex.getBindingResult().getFieldErrors().stream().map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage()).collect(Collectors.toList()))
                .build();
        return ResponseEntity.status(status).body(apiError);
    }
}
