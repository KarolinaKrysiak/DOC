package com.viatab.viatab.repository;

import com.viatab.viatab.model.Story;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StoryRepositoryTest {

    @Autowired
    private StoryRepository storyRepository;

    @Test
    void shouldSaveAndFindStory() {
        Story story = new Story();
        story.setTitle("Test Story");
        story.setContent("Test Content");
        story.setDepartment("Test Dept");

        storyRepository.save(story);

        List<Story> stories = storyRepository.findAll();
        assertThat(stories).isNotEmpty();
        assertThat(stories.get(0).getTitle()).isEqualTo("Test Story");
    }
}