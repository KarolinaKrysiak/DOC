package com.viatab.viatab.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.viatab.viatab.model.Story;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class StoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateAndGetStory() throws Exception {
        Story story = new Story();
        story.setTitle("API Test");
        story.setContent("API Content");
        story.setDepartment("API Dept");

        // Create
        mockMvc.perform(post("/api/stories")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(story)))
                .andExpect(status().isOk());

        // Get all
        mockMvc.perform(get("/api/stories"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("API Test")));
    }
}