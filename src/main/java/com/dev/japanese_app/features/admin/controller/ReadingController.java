package com.dev.japanese_app.features.admin.controller;

import com.dev.japanese_app.common.utils.ResponseUtils;
import com.dev.japanese_app.features.admin.model.reqeust.ReadingRequest;
import com.dev.japanese_app.features.admin.service.ReadingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.base.path}/v1/reading")
@RequiredArgsConstructor
@Tag(name = "Reading Controller", description = "All endpoints for reading controller")
public class ReadingController {

    private final ReadingService readingService;

    @PostMapping("/create")
    public ResponseEntity<?> createParagraph(@Valid @RequestBody ReadingRequest readingReqeust, HttpServletRequest request) {
        return ResponseUtils.buildResponseEntity(
                readingService.createReadingParagraph(readingReqeust, request),
                request
        );
    }
}
