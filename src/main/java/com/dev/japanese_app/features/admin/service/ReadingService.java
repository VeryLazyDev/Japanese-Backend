package com.dev.japanese_app.features.admin.service;

import com.dev.japanese_app.common.model.ApiResponse;
import com.dev.japanese_app.features.admin.mapper.AnswerMapper;
import com.dev.japanese_app.features.admin.mapper.QuestionMapper;
import com.dev.japanese_app.features.admin.mapper.ReadingMapper;
import com.dev.japanese_app.features.admin.model.entity.Answer;
import com.dev.japanese_app.features.admin.model.entity.Paragraph;
import com.dev.japanese_app.features.admin.model.entity.Question;
import com.dev.japanese_app.features.admin.model.reqeust.AnswerRequest;
import com.dev.japanese_app.features.admin.model.reqeust.ReadingRequest;
import com.dev.japanese_app.features.admin.repo.ReadingRepo;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReadingService {

    private final ReadingRepo readingRepo;
    private final ReadingMapper readingMapper;
    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;

    @Transactional
    public ApiResponse createReadingParagraph(ReadingRequest readingRequest, HttpServletRequest request) {
        Paragraph paragraph = readingMapper.toEntity(readingRequest);

        List<Question> questionList = readingRequest.getQuestionList()
                .stream()
                .map(qReq -> {
                    Question question = questionMapper.toEntity(qReq);

                    Set<Answer> answerList = qReq.getAnswerList()
                            .stream()
                            .map(answerMapper::toEntity).collect(Collectors.toSet());

                    question.setAnswers(answerList);
                    return question;
                })
                .toList();

        paragraph.setQuestionsList(questionList);

        paragraph = readingRepo.save(paragraph);

        return ApiResponse.builder()
                .success(true)
                .code(201)
                .message("reading created successfully")
                .content(readingMapper.toResponseDto(paragraph))
                .build();
    }
}
