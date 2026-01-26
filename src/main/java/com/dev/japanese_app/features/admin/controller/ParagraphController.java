package com.dev.japanese_app.features.admin.controller;

import com.dev.japanese_app.common.model.Create;
import com.dev.japanese_app.features.admin.model.reqeust.ParagraphRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "${api.base.path}/v{version}/paragraph", version = "1")
public class ParagraphController {

    @PostMapping("create")
    public ResponseEntity<?> createParagraph(@Validated(Create.class) ParagraphRequest paragraphRequest, HttpServletRequest request) {
        return  null;
    }
}
