package com.dev.japanese_app.features.admin.model.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionResponse {
    private Long id;
    private String question;
    private LocalDateTime createdDatetime;
    private LocalDateTime updatedDateTime;
    private List<AnswerResponse> answerList;
}
