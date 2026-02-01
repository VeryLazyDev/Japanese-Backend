package com.dev.japanese_app.features.admin.mapper.impl;

import com.dev.japanese_app.features.admin.mapper.ReadingMapper;
import com.dev.japanese_app.features.admin.model.entity.Paragraph;
import com.dev.japanese_app.features.admin.model.reponse.ParagraphResponse;
import com.dev.japanese_app.features.admin.model.reqeust.ReadingRequest;
import org.springframework.stereotype.Component;

@Component
public class ReadingMapperImpl implements ReadingMapper {
    @Override
    public ParagraphResponse toResponseDto(Paragraph entity) {
        return null;
    }

    @Override
    public Paragraph toEntity(ReadingRequest request) {
        if (request == null) throw new RuntimeException("Request Object is null");
        return Paragraph.builder()
                .paragraph(request.getParagraph())
                .paragraphType(request.getParagraphType())
                .level(request.getJapaneseLevel())
                .build();
    }
}
