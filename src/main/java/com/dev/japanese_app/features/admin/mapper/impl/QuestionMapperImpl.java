package com.dev.japanese_app.features.admin.mapper.impl;

import com.dev.japanese_app.features.admin.mapper.AnswerMapper;
import com.dev.japanese_app.features.admin.mapper.QuestionMapper;
import com.dev.japanese_app.features.admin.model.entity.Question;
import com.dev.japanese_app.features.admin.model.reponse.QuestionResponse;
import com.dev.japanese_app.features.admin.model.reqeust.QuestionRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;

@Component
@RequiredArgsConstructor
public class QuestionMapperImpl implements QuestionMapper {

    private final AnswerMapper answerMapper;
    @Override
    public QuestionResponse toResponseDto(Question entity) {
        QuestionMapper.super.validate(entity);
        return QuestionResponse.builder()
                .id(entity.getId())
                .question(entity.getQuestion())
                .createdDatetime(entity.getCreated_datetime())
                .updatedDateTime(entity.getUpdated_datetime())
                .answerList(answerMapper.toResponseList(entity.getAnswerList()))
//                .answerList(new HashSet<>(answerMapper.toResponseList(new ArrayList<>(entity.getAnswerSet()))))
                .build();
    }

    @Override
    public Question toEntity(QuestionRequest request) {
        if (request == null) throw new RuntimeException("Request Object is null");
        return Question.builder()
                .question(request.getQuestion())
                .answerList(request.getAnswerList())
                .build();
    }
}
