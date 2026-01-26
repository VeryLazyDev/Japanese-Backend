package com.dev.japanese_app.features.admin.mapper;

import com.dev.japanese_app.common.mapper.BaseResponseMapper;
import com.dev.japanese_app.features.admin.model.entity.Question;
import com.dev.japanese_app.features.admin.model.reponse.QuestionResponse;
import com.dev.japanese_app.features.admin.model.reqeust.QuestionRequest;
import org.springframework.stereotype.Component;

@Component
public interface QuestionMapper extends BaseResponseMapper<Question, QuestionResponse, QuestionRequest> {

}
