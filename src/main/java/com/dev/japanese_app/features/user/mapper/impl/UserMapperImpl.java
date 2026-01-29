package com.dev.japanese_app.features.user.mapper.impl;

import com.dev.japanese_app.features.user.mapper.UserMapper;
import com.dev.japanese_app.features.user.model.entity.User;
import com.dev.japanese_app.features.user.model.reponse.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {
    @Override
    public UserResponse toResponseDto(User entity) {
        UserMapper.super.validate(entity);
        return UserResponse.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .createdDateTime(entity.getCreatedDateTime())
                .build();
    }

    @Override
    public User toEntity(Object request) {
        return null;
    }
}
