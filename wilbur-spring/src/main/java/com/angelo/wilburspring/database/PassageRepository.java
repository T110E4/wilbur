package com.angelo.wilburspring.database;

import com.angelo.wilburspring.models.Passage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassageRepository extends JpaRepository<Passage, Long> {
}