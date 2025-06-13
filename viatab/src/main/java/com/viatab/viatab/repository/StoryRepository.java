package com.viatab.viatab.repository;

import com.viatab.viatab.model.Story;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoryRepository extends JpaRepository<Story, Long> {
}