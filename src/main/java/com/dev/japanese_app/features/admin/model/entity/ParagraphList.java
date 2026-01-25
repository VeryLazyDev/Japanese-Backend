package com.dev.japanese_app.features.admin.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder
@Table(name = "reading_paragraph")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParagraphList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String paragraph;

    @Column(nullable = false)
    private String paragraphType;

    @Enumerated(EnumType.STRING)
    private JapaneseLevel level;

    @Column(nullable = false,updatable = false)
    private LocalDateTime created_datetime;

    @Column(nullable = false)
    private LocalDateTime updated_datetime;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "paragraph_id")
    List<QuestionList> questionsList;

    @OneToMany(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    List<AnswerList> answerList;

    @PrePersist
    protected void onCreate(){
        this.created_datetime = LocalDateTime.now();
        this.updated_datetime = LocalDateTime.now();
    }


}
