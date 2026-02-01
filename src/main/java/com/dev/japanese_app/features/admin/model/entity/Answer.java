package com.dev.japanese_app.features.admin.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "answer")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String answer;

    @Column(nullable = false)
    private Boolean correct_answer;

    @ManyToOne
    @JoinColumn(name = "fk_question_id", referencedColumnName = "id")
    private Question question;

}