package com.dev.japanese_app.features.admin.model.reqeust;

import com.dev.japanese_app.common.model.Create;
import com.dev.japanese_app.common.model.Update;
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
    @NotNull(groups = Update.class,message = "answer id must not be null.")
    private Long id;

    @NotNull(groups = {Update.class, Create.class}, message = "answer must not be null")
    private String answer;

    @NotNull(groups = {Update.class, Create.class}, message = "correct_answer must not be null")
    private Boolean correct_answer;
}
