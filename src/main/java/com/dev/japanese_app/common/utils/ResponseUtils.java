package com.dev.japanese_app.common.utils;

import com.dev.japanese_app.common.model.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public class ResponseUtils {

    public static ResponseEntity<?> buildResponseEntity(ApiResponse<?> data, HttpServletRequest request) {
        if (data.getMetadata() == null) {
            final String method = request.getMethod();
            final String endpoint = request.getRequestURI();
            data.setMetadata(new HashMap<>());
            data.getMetadata().put("method", method);
            data.getMetadata().put("endpoint", endpoint);
        }
        return ResponseEntity.status(data.getCode()).body(data);
    }
}
