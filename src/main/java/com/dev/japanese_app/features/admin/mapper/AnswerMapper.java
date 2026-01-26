package com.dev.japanese_app.features.admin.mapper;

import com.dev.japanese_app.common.mapper.BaseResponseMapper;
import com.dev.japanese_app.features.admin.model.entity.Answer;
import com.dev.japanese_app.features.admin.model.reponse.AnswerResponse;
import com.dev.japanese_app.features.admin.model.reqeust.AnswerRequest;

public interface AnswerMapper extends BaseResponseMapper<Answer, AnswerResponse, AnswerRequest> {
}
