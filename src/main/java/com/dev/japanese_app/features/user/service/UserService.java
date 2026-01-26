package com.dev.japanese_app.features.user.service;

import com.dev.japanese_app.common.model.ApiResponse;
import com.dev.japanese_app.features.user.mapper.UserMapper;
import com.dev.japanese_app.features.user.model.entity.User;
import com.dev.japanese_app.features.user.model.reponse.UserResponse;
import com.dev.japanese_app.features.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Transactional
    public ApiResponse<UserResponse> registerUser(String username, String email, String password){
        if (getUserByEmail(email) != null) throw new RuntimeException("user already exited with this email");

        User user = User.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();

        log.info("user created successfully !!");
        return ApiResponse.<UserResponse>builder()
                .success(true)
                .code(201)
                .message("user register successfully !!")
                .content(userMapper.toResponseDto(userRepository.save(user)))
                .build();
    }

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found with email : " + email));
    }

    @Transactional(readOnly = true)
    public ApiResponse<?> getAllUser(){
        return ApiResponse.builder()
                .success(true)
                .code(201)
                .message("user register successfully !!")
                .content(userRepository.findAll().stream().map(userMapper::toResponseDto).toList())
                .build();
    }


}
