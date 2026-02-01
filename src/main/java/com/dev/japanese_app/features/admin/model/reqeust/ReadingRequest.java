package com.dev.japanese_app.features.admin.model.reqeust;

import com.dev.japanese_app.common.constant.JapaneseLevel;
import com.dev.japanese_app.common.constant.ParagraphType;
import com.dev.japanese_app.common.model.Create;
import com.dev.japanese_app.common.model.Update;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadingRequest {
    @NotNull(groups = Update.class,message = "paragraph id must not be null.")
    private Long id;

    @NotNull(groups = {Update.class, Create.class}, message = "paragraph must not be null")
    private String paragraph;

    @NotNull(groups = {Update.class, Create.class}, message = "paragraphType must not be null")
    private ParagraphType paragraphType;

    @NotNull(groups = {Update.class, Create.class}, message = "japaneseLevel must not be null")
    private JapaneseLevel japaneseLevel;

    @NotNull(message = "questions list must not be null")
    @Size(min = 1, message = "there must be one question at least.")
    private List<QuestionRequest> questionList;

}
