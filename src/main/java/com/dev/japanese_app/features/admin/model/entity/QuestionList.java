package com.dev.japanese_app.features.admin.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false,updatable = false)
    private LocalDateTime created_datetime;

    @Column(nullable = false)
    private LocalDateTime updated_datetime;

    @PrePersist
    protected void onCreate(){
        this.created_datetime = LocalDateTime.now();
        this.updated_datetime = LocalDateTime.now();
    }
}
