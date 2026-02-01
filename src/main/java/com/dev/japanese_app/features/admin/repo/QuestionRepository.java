package com.dev.japanese_app.features.admin.repo;

import com.dev.japanese_app.features.admin.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}
