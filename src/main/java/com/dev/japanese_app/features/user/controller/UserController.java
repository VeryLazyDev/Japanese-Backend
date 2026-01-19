package com.dev.japanese_app.features.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("${api.base.path}/v1/user")
public class UserController {

    @GetMapping
    public ResponseEntity<?> hello(HttpServletRequest request){
        Map<String, String> response = new HashMap<>();
        response.put("msg", "server is running at 8080");
        return ResponseEntity.ok(response);    }
}
