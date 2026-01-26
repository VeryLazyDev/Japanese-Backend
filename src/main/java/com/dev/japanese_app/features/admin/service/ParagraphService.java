package com.dev.japanese_app.features.admin.service;

import com.dev.japanese_app.common.model.ApiResponse;
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

    @Transactional
    public ApiResponse<?> createParagraph(ParagraphRequest paragraphRequest , HttpServletRequest request) {

    }

}
