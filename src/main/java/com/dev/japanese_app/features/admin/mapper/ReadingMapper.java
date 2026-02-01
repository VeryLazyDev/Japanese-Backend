package com.dev.japanese_app.features.admin.mapper;

import com.dev.japanese_app.common.mapper.BaseResponseMapper;
import com.dev.japanese_app.features.admin.model.entity.Paragraph;
import com.dev.japanese_app.features.admin.model.reponse.ParagraphResponse;
import com.dev.japanese_app.features.admin.model.reqeust.ReadingRequest;

public interface ReadingMapper extends BaseResponseMapper<Paragraph, ParagraphResponse, ReadingRequest> {
}
