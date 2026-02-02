package com.dev.japanese_app.features.admin.model.reqeust;

import com.dev.japanese_app.common.constant.JapaneseLevel;
import com.dev.japanese_app.common.constant.ParagraphType;
import com.dev.japanese_app.common.model.Create;
import com.dev.japanese_app.common.model.Update;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParagraphRequest {

    @NotNull(groups = Update.class,message = "paragraph id must not be null.")
    private Long id;

    @NotNull(groups = {Update.class, Create.class}, message = "paragraph must not be null")
    private String paragraph;

    @NotNull(groups = {Update.class, Create.class}, message = "paragraphType must not be null")
    private ParagraphType paragraphType;

    @NotNull(groups = {Update.class, Create.class}, message = "japaneseLevel must not be null")
    private JapaneseLevel japaneseLevel;

    @NotNull(groups = {Update.class, Create.class}, message = "questions list must not be null")
    private List<QuestionRequest> questionList;
}
