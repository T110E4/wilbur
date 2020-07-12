package com.angelo.wilburspring.database;

import com.angelo.wilburspring.models.PassageResult;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassageResultRepository extends JpaRepository<PassageResult, Long> {
}