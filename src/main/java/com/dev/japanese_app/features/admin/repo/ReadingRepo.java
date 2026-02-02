package com.dev.japanese_app.features.admin.repo;

import com.dev.japanese_app.features.admin.model.entity.Paragraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadingRepo extends JpaRepository<Paragraph, Long> {
}
