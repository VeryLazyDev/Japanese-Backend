package com.dev.japanese_app.features.admin.model.reqeust;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionRequest {
    @NotNull( message = "question must not be null")
    private String question;

    @NotNull(message = "answer lists must not be null")
    @Size(min = 2, max = 5, message = "the numbers of answer must be between 2 and 5")
    private Set<AnswerRequest> answerList;
}
