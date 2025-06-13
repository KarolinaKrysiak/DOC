package com.viatab.viatab.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StoryTest {

    @Test
    void gettersAndSetters_shouldWork() {
        Story story = new Story();
        story.setId(1L);
        story.setTitle("Test Title");
        story.setContent("Test Content");
        story.setDepartment("Test Dept");

        assertEquals(1L, story.getId());
        assertEquals("Test Title", story.getTitle());
        assertEquals("Test Content", story.getContent());
        assertEquals("Test Dept", story.getDepartment());
    }
}