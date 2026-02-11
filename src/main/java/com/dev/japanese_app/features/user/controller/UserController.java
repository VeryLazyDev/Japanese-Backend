package com.dev.japanese_app.features.user.controller;

import com.dev.japanese_app.common.utils.ResponseUtils;
import com.dev.japanese_app.features.user.model.reponse.UserResponse;
import com.dev.japanese_app.features.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("${api.base.path}/v1/admin")
@RequiredArgsConstructor
@Tag(name = "User Controller", description = "All endpoints for user controller")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> hello(HttpServletRequest request) {
        Map<String, String> response = new HashMap<>();
        response.put("msg", "server is running at 8080");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @RequestParam(name = "email") String email,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "password") String password,
            HttpServletRequest request
    ) {
        return ResponseUtils.buildResponseEntity(userService.registerUser(name, email, password), request);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUser(HttpServletRequest request){
        return ResponseUtils.buildResponseEntity(userService.getAllUser(), request);
    }
}
