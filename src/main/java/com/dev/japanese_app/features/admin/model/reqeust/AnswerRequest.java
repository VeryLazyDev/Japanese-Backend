package com.dev.japanese_app.features.admin.model.reqeust;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AnswerRequest {
    @NotNull(message = "paragraph must not be null")
    private String answer;

    @NotNull(message = "correct answer or not must be not null")
    private Boolean correct_answer;
}
