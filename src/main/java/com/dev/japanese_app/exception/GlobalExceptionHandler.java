package com.dev.japanese_app.exception;

import com.dev.japanese_app.common.model.ApiResponse;
import com.dev.japanese_app.common.utils.ResponseUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, final HttpServletRequest request){
        ApiResponse<?> response = ApiResponse.builder()
                .success(false)
                .code(500)
                .message(ex.getMessage())
                .content(null)
                .build();
        return ResponseUtils.buildResponseEntity(response, request);
    }

    @ExceptionHandler({IllegalArgumentException.class, MissingPathVariableException.class})
    public ResponseEntity<?> handleBadRequest(Exception ex, HttpServletRequest request) {
        ApiResponse<?> response = ApiResponse.builder()
                .success(false)
                .code(400)
                .message(ex.getMessage())
                .content(null)
                .build();
        return ResponseUtils.buildResponseEntity(response, request);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
        ApiResponse<?> response = ApiResponse.builder()
                .success(false)
                .code(400)
                .message(ex.getMessage())
                .content(null)
                .build();
        return ResponseUtils.buildResponseEntity(response, request);
    }

}
