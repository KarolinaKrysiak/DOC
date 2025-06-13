package com.viatab.viatab.controller;

import com.viatab.viatab.model.Story;
import com.viatab.viatab.repository.StoryRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/stories")

public class StoryController {
    private final StoryRepository storyRepository;

    public StoryController(StoryRepository storyRepository) {
        this.storyRepository = storyRepository;
    }

    @GetMapping
    public List<Story> getAllStories() {
        return storyRepository.findAll();
    }

    @PostMapping
    public Story addStory(@RequestBody Story story) {
        return storyRepository.save(story);
    }

    @DeleteMapping("/{id}")
    public void deleteStory(@PathVariable Long id) {
        storyRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Story updateStory(@PathVariable Long id, @RequestBody Story story) {
        story.setId(id);
        return storyRepository.save(story);
    }
}

