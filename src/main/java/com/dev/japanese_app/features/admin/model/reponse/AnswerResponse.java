package com.dev.japanese_app.features.admin.model.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerResponse {
    private Long id;
    private String answer;
    private Boolean correct_answer;
}
