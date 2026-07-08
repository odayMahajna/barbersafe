package com.barbersafe.Exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.barbersafe.DTOs.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

     @ExceptionHandler(IllegalArgumentException.class)
     public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex) {
          ErrorResponse error = new ErrorResponse(ex.getMessage(), Instant.now());
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
     }
}