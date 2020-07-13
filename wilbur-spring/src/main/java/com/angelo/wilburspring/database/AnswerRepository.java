package com.angelo.wilburspring.database;

import com.angelo.wilburspring.models.Answer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {
}