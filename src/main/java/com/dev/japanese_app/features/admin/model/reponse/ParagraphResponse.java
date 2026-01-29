package com.dev.japanese_app.features.admin.model.reponse;

import com.dev.japanese_app.common.constant.JapaneseLevel;
import com.dev.japanese_app.common.constant.ParagraphType;
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
public class ParagraphResponse {

    private Long id;
    private String paragraph;
    private ParagraphType paragraphType;
    private JapaneseLevel level;
    private LocalDateTime createdDatetime;
    private LocalDateTime updatedDateTime;
    private List<QuestionResponse> questionList;
}
