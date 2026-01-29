package com.dev.japanese_app.features.admin.model.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionResponse {
    private Long id;
    private String question;
    private Set<AnswerResponse> answerList;
    private Long correctAnswerId;
}
