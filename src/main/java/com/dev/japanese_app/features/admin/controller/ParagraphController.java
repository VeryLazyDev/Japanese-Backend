package com.dev.japanese_app.features.admin.controller;

import com.dev.japanese_app.common.constant.JapaneseLevel;
import com.dev.japanese_app.common.constant.ParagraphType;
import com.dev.japanese_app.common.model.Create;
import com.dev.japanese_app.common.utils.ResponseUtils;
import com.dev.japanese_app.features.admin.model.reqeust.AnswerRequest;
import com.dev.japanese_app.features.admin.model.reqeust.ParagraphRequest;
import com.dev.japanese_app.features.admin.model.reqeust.QuestionRequest;
import com.dev.japanese_app.features.admin.service.ParagraphService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${api.base.path}/v1/paragraph")
@RequiredArgsConstructor
public class ParagraphController {

    private final ParagraphService paragraphService;
    @Operation(summary = "Create a new paragraph", description = "Add a new paragraph to the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paragraph is created successfully",
                    content = @Content(schema = @Schema(implementation = ParagraphRequest.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request data",
                    content = @Content(schema = @Schema()))
    })
    @PostMapping("/create")
    public ResponseEntity<?> createParagraph(@Validated(Create.class) @RequestBody ParagraphRequest paragraphRequest, HttpServletRequest request) {
        return ResponseUtils.buildResponseEntity(
                paragraphService.createParagraph(paragraphRequest, request),
                request
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getParagraphById(@PathVariable Long id, HttpServletRequest request) {
        return ResponseUtils.buildResponseEntity(
                paragraphService.getParagraphById(id, request),
                request
        );
    }
    @Operation(summary = "Update a paragraph by id", description = "Update an existing paragraph's details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paragraph is updated successfully",
                    content = @Content(schema = @Schema(implementation = ParagraphRequest.class))),
            @ApiResponse(responseCode = "404", description = "Paragraph not found",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/update-paragraph/{id}")
    public ResponseEntity<?> updateParagraphByID( @RequestBody ParagraphRequest paragraphRequest, @PathVariable Long id, HttpServletRequest request){
        return ResponseUtils.buildResponseEntity(
                paragraphService.updateParagraphById(id, paragraphRequest, request),
                request
        );
    }
    @Operation(summary = "Update a question by id", description = "Update an existing question's details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Question is updated successfully",
                    content = @Content(schema = @Schema(implementation = QuestionRequest.class))),
            @ApiResponse(responseCode = "404", description = "Question not found",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/update-question/{id}")
    public ResponseEntity<?> updateQuestionID(@RequestBody QuestionRequest questionRequest, @PathVariable Long id, HttpServletRequest request){
        return ResponseUtils.buildResponseEntity(
                paragraphService.updateQuestionById(id, questionRequest, request),
                request
        );
    }
    @Operation(summary = "Update an answer by id", description = "Update an existing answer's details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Answer is updated successfully",
                    content = @Content(schema = @Schema(implementation = AnswerRequest.class))),
            @ApiResponse(responseCode = "404", description = "Answer not found",
                    content = @Content(schema = @Schema()))
    })
    @PutMapping("/update-answer/{id}")
    public ResponseEntity<?> updateAnswerByID(@RequestBody AnswerRequest answerRequest, @PathVariable Long id, HttpServletRequest request){
        return ResponseUtils.buildResponseEntity(
                paragraphService.updateAnswerByID(id, answerRequest, request),
                request
        );
    }
    @Operation(summary = "Delete a paragraph by id", description = "Delete a paragraph")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Paragraph is deleted successfully",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "Paragraph not found",
                    content = @Content(schema = @Schema()))
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteParagraphById(@PathVariable Long id, HttpServletRequest request){
        return ResponseUtils.buildResponseEntity(
                paragraphService.deleteParagraph(id, request),
                request
        );
    }
    @Operation(summary = "Get all paragraph", description = "Get all paragraph with pages and format")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success",
                    content = @Content(schema = @Schema())),
            @ApiResponse(responseCode = "404", description = "404",
                    content = @Content(schema = @Schema()))
    })
    @GetMapping("/all")
    public ResponseEntity<?> getAllParagraph(
            @RequestParam(name = "size", defaultValue = "10")
            @Min(0) Integer size,

            @RequestParam(name = "page", defaultValue = "0")
            @Min(0) Integer page,

            @RequestParam(name = "keyword", required = false)
            String keyword,

            @Parameter(description = "Japanese Level filter (Optional)")
            @RequestParam(name = "level", required = false)
            JapaneseLevel japaneseLevel,

            @Parameter(description = "Paragraph filter (Optional)")
            @RequestParam(name = "paragraphType", required = false)
            ParagraphType paragraphType,

            @RequestParam(name = "sortField", defaultValue = "id")
            @Pattern(regexp = "^(id|paragraph)$", message = "Invalid column name")
            String sortField,

            @RequestParam(name = "sortOrder", defaultValue = "desc", required = false)
            String sortOrder,

            HttpServletRequest request
    ) {
        Sort sort = "desc".equalsIgnoreCase(sortOrder) ? Sort.by(sortField).descending() : Sort.by(sortField).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return ResponseUtils.buildResponseEntity(paragraphService.getAllParagraph(keyword, japaneseLevel,paragraphType, pageable, request),request);
    }


}
