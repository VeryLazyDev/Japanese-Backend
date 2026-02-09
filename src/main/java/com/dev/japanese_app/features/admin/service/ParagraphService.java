package com.dev.japanese_app.features.admin.service;

import com.dev.japanese_app.common.constant.JapaneseLevel;
import com.dev.japanese_app.common.constant.ParagraphType;
import com.dev.japanese_app.common.model.ApiResponse;
import com.dev.japanese_app.common.utils.PaginationUtils;
import com.dev.japanese_app.features.admin.mapper.AnswerMapper;
import com.dev.japanese_app.features.admin.mapper.ParagraphMapper;
import com.dev.japanese_app.features.admin.mapper.QuestionMapper;
import com.dev.japanese_app.features.admin.model.entity.Answer;
import com.dev.japanese_app.features.admin.model.entity.Paragraph;
import com.dev.japanese_app.features.admin.model.entity.Question;
import com.dev.japanese_app.features.admin.model.reponse.ParagraphResponse;
import com.dev.japanese_app.features.admin.model.reqeust.AnswerRequest;
import com.dev.japanese_app.features.admin.model.reqeust.ParagraphRequest;
import com.dev.japanese_app.features.admin.model.reqeust.QuestionRequest;
import com.dev.japanese_app.features.admin.repo.AnswerRepository;
import com.dev.japanese_app.features.admin.repo.ParagraphRepository;
import com.dev.japanese_app.features.admin.repo.QuestionRepository;
import jakarta.persistence.criteria.Predicate;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class ParagraphService {

    private final ParagraphRepository paragraphRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final ParagraphMapper paragraphMapper;
    private final QuestionMapper questionMapper;
    private final AnswerMapper answerMapper;

    @Transactional
    public ApiResponse<?> createParagraph(ParagraphRequest paragraphRequest, HttpServletRequest request) {

        /*check user is valid or not from access token*/
        //  VALIDATION SECTION
        /**/
        Paragraph savedParagraph = paragraphMapper.toEntity(paragraphRequest);
        if (savedParagraph.getQuestionList() != null) {
            savedParagraph.getQuestionList().forEach((Question question) -> {
                question.setParagraph(savedParagraph);
                if (question.getAnswers() != null) {
                    question.getAnswers().forEach(answer -> answer.setQuestion(question));
                }
            });
        }
        return ApiResponse.builder().success(true).code(201).message("Paragraph is created successfully!!").content(paragraphMapper.toResponseDto(paragraphRepository.save(savedParagraph))).build();
    }

    @Transactional
    public ApiResponse<?> updateParagraphById(Long id, ParagraphRequest paragraphRequest, HttpServletRequest request) {
        Paragraph savedParagraph = paragraphRepository.findById(id).orElseThrow(() -> new RuntimeException("Paragraph not found with id " + id));
        savedParagraph.setParagraph(paragraphRequest.getParagraph());
        savedParagraph.setParagraphType(paragraphRequest.getParagraphType());
        savedParagraph.setLevel(paragraphRequest.getJapaneseLevel());
        return ApiResponse.builder().success(true).code(202).message("Paragraph is updated successfully!!")
                .content(paragraphMapper.toResponseDto(paragraphRepository.save(savedParagraph))).build();
    }
    @Transactional
    public ApiResponse<?> updateQuestionById(Long id, QuestionRequest questionRequest, HttpServletRequest request) {
        Question savedQuestion = questionRepository.findById(id).orElseThrow(() -> new RuntimeException("Question not found with id " + id));
        savedQuestion.setQuestion(questionRequest.getQuestion());
        return ApiResponse.builder().success(true).code(202).message("Question is updated successfully!!")
                .content(questionMapper.toResponseDto(questionRepository.save(savedQuestion))).build();
    }
    @Transactional
    public ApiResponse<?> updateAnswerByID(Long id, AnswerRequest answerRequest, HttpServletRequest request){
        Answer savedAnswer = answerRepository.findById(id).orElseThrow((() -> new RuntimeException("Answer not found with id " + id)));
        savedAnswer.setAnswer(answerRequest.getAnswer());
        savedAnswer.setCorrect_answer(answerRequest.getCorrect_answer());
        return ApiResponse.builder().success(true).code(202).message("Answer is updated successfully")
                .content(answerMapper.toResponseDto(answerRepository.save(savedAnswer))).build();
    }

    @Transactional
    public ApiResponse<?> deleteParagraph(Long id, HttpServletRequest request) {
        paragraphRepository.findById(id).orElseThrow(() -> new RuntimeException("Paragraph not found with id " + id));
        paragraphRepository.deleteById(id);
        return ApiResponse.builder().success(true).code(204).message("Paragraph is deleted successfully!!").content(null).build();
    }

    @Transactional(readOnly = true)
    public ApiResponse<ParagraphResponse> getParagraphById(Long id, HttpServletRequest request) {
        Paragraph paragraph = paragraphRepository.findById(id).orElseThrow(() -> new RuntimeException("Paragraph not found with id : " + id));
        ParagraphResponse response = paragraphMapper.toResponseDto(paragraph);
        return ApiResponse.<ParagraphResponse>builder().success(true).code(200).message("Paragraph that equals by id " + id).content(response).build();
    }

    @Transactional(readOnly = true)
    public ApiResponse<List<ParagraphResponse>> getAllParagraph(String keyword, JapaneseLevel level, ParagraphType paragraphType, Pageable pageable, HttpServletRequest request) {
        Specification<Paragraph> specs = (root, query, cb) -> {
            List<Predicate> predicateList = new ArrayList<>();
            // keyword filter
            if (keyword != null && !keyword.isEmpty()) {
                String likePattern = "%" + keyword.toLowerCase() + "%";
                predicateList.add(cb.or(cb.like(root.get("paragraph"), likePattern), cb.like(root.get("level"), likePattern), cb.like(root.get("paragraphType"), likePattern)));
            }
            // level filter
            if (level != null) {
                predicateList.add(cb.equal(root.get("level"), level));
            }
            // paragraphType filter (long, medium, short)
            if (paragraphType != null) {
                predicateList.add(cb.equal(root.get("paragraphType"), paragraphType));
            }
            return cb.and(predicateList.toArray(new Predicate[0]));
        };

        // retrieve from db
        Page<Paragraph> paragraphPage = paragraphRepository.findAll(specs, pageable);
        // define pagination metadata
        Map<String, Object> paginationMetaData = PaginationUtils.buildPaginationMetaData(paragraphPage);
        List<ParagraphResponse> paragraphResponseList = paragraphPage.stream().map(paragraphMapper::toResponseDto).toList();

        return ApiResponse.<List<ParagraphResponse>>builder().success(true).code(200).message("paragraph list retrieved successfully !!").content(paragraphResponseList).metadata(paginationMetaData).build();
    }

}
