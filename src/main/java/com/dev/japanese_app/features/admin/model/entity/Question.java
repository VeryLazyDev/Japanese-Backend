package com.dev.japanese_app.features.admin.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String question;

    @ManyToOne
    @JoinColumn(name = "fk_paragraph_id", referencedColumnName = "id")
    private Paragraph paragraph;

    @Column(nullable = false,updatable = false)
    private LocalDateTime created_datetime;

    private LocalDateTime updated_datetime;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Answer> answers = new ArrayList<>();

    @PrePersist
    protected void onCreate(){
        this.created_datetime = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updated_datetime = LocalDateTime.now();
    }


}
