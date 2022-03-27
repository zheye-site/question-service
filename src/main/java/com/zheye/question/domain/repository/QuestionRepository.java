package com.zheye.question.domain.repository;

import com.zheye.question.domain.model.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, String> {
}
