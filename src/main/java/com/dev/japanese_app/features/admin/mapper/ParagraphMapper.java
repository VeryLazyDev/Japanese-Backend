package com.dev.japanese_app.features.admin.mapper;

import com.dev.japanese_app.common.mapper.BaseResponseMapper;
import com.dev.japanese_app.features.admin.model.entity.Paragraph;
import com.dev.japanese_app.features.admin.model.reponse.ParagraphResponse;

public interface ParagraphMapper extends BaseResponseMapper<Paragraph, ParagraphResponse> {
    @Override
    default ParagraphResponse toDto(Paragraph paragraph){
        if (paragraph == null) return null;
        return ParagraphResponse.builder()
                .id(paragraph.getId())
                .paragraph(paragraph.getParagraph())
                .paragraphType(paragraph.getParagraphType())
                .level(paragraph.getLevel())
                .createdDatetime(paragraph.getCreated_datetime())
                .updatedDateTime(paragraph.getUpdated_datetime())
                .build();
    }
}
