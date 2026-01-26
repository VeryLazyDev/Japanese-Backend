package com.dev.japanese_app.features.admin.service;

import com.dev.japanese_app.common.model.ApiResponse;
import com.dev.japanese_app.features.admin.mapper.ParagraphMapper;
import com.dev.japanese_app.features.admin.mapper.QuestionMapper;
import com.dev.japanese_app.features.admin.model.entity.Paragraph;
import com.dev.japanese_app.features.admin.model.reponse.ParagraphResponse;
import com.dev.japanese_app.features.admin.model.reqeust.ParagraphRequest;
import com.dev.japanese_app.features.admin.repo.ParagraphRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ParagraphService {

    private final ParagraphRepository paragraphRepository;
    private final ParagraphMapper paragraphMapper;

    @Transactional
    public ApiResponse<?> createParagraph(ParagraphRequest paragraphRequest, HttpServletRequest request) {

        /*check user is valid or not from access token*/
        //  VALIDATION SECTION
        /**/

        Paragraph paragraph = paragraphRepository.save(
                paragraphMapper.toEntity(paragraphRequest)
        );
        return ApiResponse.builder()
                .success(true)
                .code(201)
                .message("Paragraph create successfully!!")
                .content(paragraphMapper.toResponseDto(paragraph))
                .build();
    }

    @Transactional(readOnly = true)
    public ApiResponse<ParagraphResponse> getParagraphById(Long id, HttpServletRequest request) {
        Paragraph paragraph = paragraphRepository.findById(id).orElseThrow(() -> new RuntimeException("Paragraph not found with id : " + id));
        ParagraphResponse response = paragraphMapper.toResponseDto(paragraph);
        return ApiResponse.<ParagraphResponse>builder()
                .success(true)
                .code(200)
                .message("Paragraph that equals by id " + id)
                .content(response)
                .build();
    }

}
