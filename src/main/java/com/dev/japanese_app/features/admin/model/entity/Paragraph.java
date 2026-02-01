package com.dev.japanese_app.features.admin.model.entity;

import com.dev.japanese_app.common.constant.JapaneseLevel;
import com.dev.japanese_app.common.constant.ParagraphType;
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
public class Paragraph {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(columnDefinition = "TEXT", nullable = false)
    private String paragraph;

    @Enumerated(EnumType.STRING)
    private ParagraphType paragraphType;

    @Enumerated(EnumType.STRING)
    private JapaneseLevel level;

    @Column(nullable = false,updatable = false)
    private LocalDateTime created_datetime;

    @Column(nullable = true)
    private LocalDateTime updated_datetime;

    @OneToMany(mappedBy = "paragraph", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Question> questionList;

    @PrePersist
    protected void onCreate(){
        this.created_datetime = LocalDateTime.now();
    }

    @PreUpdate
    private void onUpdate(){
        this.updated_datetime = LocalDateTime.now();
    }


}
