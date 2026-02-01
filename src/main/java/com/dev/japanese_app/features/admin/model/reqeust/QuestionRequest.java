package com.dev.japanese_app.features.admin.model.reqeust;

import com.dev.japanese_app.common.model.Create;
import com.dev.japanese_app.common.model.Update;
import com.dev.japanese_app.features.admin.model.entity.Answer;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionRequest {
    @NotNull(groups = Update.class,message = "question id must not be null.")
    private Long id;

    @NotNull(groups = {Update.class, Create.class}, message = "question must not be null")
    private String question;

    @NotNull(groups = {Update.class, Create.class}, message = "japaneseLevel must not be null")
    private List<Answer> answerList;
//    private Set<Answer> answerSet;
}
