package com.dev.japanese_app.features.admin.mapper.impl;

import com.dev.japanese_app.features.admin.mapper.QuestionMapper;
import com.dev.japanese_app.features.admin.model.entity.Question;
import com.dev.japanese_app.features.admin.model.reponse.QuestionResponse;
import com.dev.japanese_app.features.admin.model.reqeust.QuestionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionMapperImpl implements QuestionMapper {
    @Override
    public QuestionResponse toResponseDto(Question entity) {
        QuestionMapper.super.validate(entity);
        return QuestionResponse.builder()
                .id(entity.getId())
                .question(entity.getQuestion())
                .correctAnswerId(entity.getCorrectAnswer().getId())
                .build();
    }


    @Override
    public Question toEntity(QuestionRequest request) {
        if (request == null) throw new RuntimeException("Request Object is null");
        return null;
    }
}
