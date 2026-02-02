package com.dev.japanese_app.features.admin.mapper.impl;

import com.dev.japanese_app.features.admin.mapper.ParagraphMapper;
import com.dev.japanese_app.features.admin.mapper.QuestionMapper;
import com.dev.japanese_app.features.admin.model.entity.Paragraph;
import com.dev.japanese_app.features.admin.model.reponse.ParagraphResponse;
import com.dev.japanese_app.features.admin.model.reqeust.ParagraphRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ParagraphMapperImpl implements ParagraphMapper {

    private final QuestionMapper questionMapper;
    @Override
    public ParagraphResponse toResponseDto(Paragraph entity) {
        ParagraphMapper.super.validate(entity);
        return ParagraphResponse.builder()
                .id(entity.getId())
                .paragraph(entity.getParagraph())
                .paragraphType(entity.getParagraphType())
                .level(entity.getLevel())
                .questionList(questionMapper.toResponseList(entity.getQuestionList()))
                .createdDatetime(entity.getCreated_datetime())
                .updatedDateTime(entity.getUpdated_datetime())
                .build();
    }

    @Override
    public Paragraph toEntity(ParagraphRequest request) {
        if (request == null) throw new RuntimeException("Request Object is null");
        return Paragraph.builder()
                .paragraph(request.getParagraph())
                .paragraphType(request.getParagraphType())
                .level(request.getJapaneseLevel())
                .questionList(request.getQuestionList().stream().map(questionMapper::toEntity).toList())
                .build();
    }
}
