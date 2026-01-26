package com.dev.japanese_app.features.admin.controller;

import com.dev.japanese_app.common.model.Create;
import com.dev.japanese_app.common.utils.ResponseUtils;
import com.dev.japanese_app.features.admin.model.reqeust.ParagraphRequest;
import com.dev.japanese_app.features.admin.service.ParagraphService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "${api.base.path}/v{version}/paragraph", version = "1")
@RequiredArgsConstructor
public class ParagraphController {

    private final ParagraphService paragraphService;

    @PostMapping("/create")
    public ResponseEntity<?> createParagraph(@Validated(Create.class) @RequestBody ParagraphRequest paragraphRequest, HttpServletRequest request) {
        System.err.println("call post");
        return ResponseUtils.buildResponseEntity(
                paragraphService.createParagraph(paragraphRequest, request),
                request
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getParagraphById(@PathVariable Long id, HttpServletRequest request) {

        System.err.println("call get");
        return ResponseUtils.buildResponseEntity(
                paragraphService.getParagraphById(id, request),
                request
        );
    }
}
