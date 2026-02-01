package com.dev.japanese_app.features.admin.mapper.impl;

import com.dev.japanese_app.features.admin.mapper.AnswerMapper;
import com.dev.japanese_app.features.admin.model.entity.Answer;
import com.dev.japanese_app.features.admin.model.reponse.AnswerResponse;
import com.dev.japanese_app.features.admin.model.reqeust.AnswerRequest;
import org.springframework.stereotype.Component;

@Component
public class AnswerMapperImpl implements AnswerMapper {
    @Override
    public AnswerResponse toResponseDto(Answer entity) {
        AnswerMapper.super.validate(entity);
        return AnswerResponse.builder()
                .id(entity.getId())
                .answer(entity.getAnswer())
                .correct_answer(entity.getCorrect_answer())
                .build();
    }

    @Override
    public Answer toEntity(AnswerRequest request) {
        if (request == null) throw new RuntimeException("Request Object is null");
        return Answer.builder()
                .answer(request.getAnswer())
                .correct_answer(request.getCorrect_answer())
                .build();
    }
}
