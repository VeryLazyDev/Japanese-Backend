package com.dev.japanese_app.features.admin.repo;

import com.dev.japanese_app.features.admin.model.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AnswerRepository extends JpaRepository<Answer,Long> , JpaSpecificationExecutor<Answer> {
}
