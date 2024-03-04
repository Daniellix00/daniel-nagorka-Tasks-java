package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDTo;

import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@SpringJUnitWebConfig
@WebMvcTest(TaskController.class)
class TaskControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;
    @Autowired
    private ObjectMapper objectMapper;



    @Test
    void getTasks() throws Exception {
        // Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1L, "Test Task 1", "Test Content 1"));
        tasks.add(new Task(2L, "Test Task 2", "Test Content 2"));
        List<TaskDTo> taskDtos = List.of(
                new TaskDTo(1L, "Test Task 1", "Test Content 1"),
                new TaskDTo(2L, "Test Task 2", "Test Content 2")
        );
        when(service.getAllTasks()).thenReturn(tasks);
        when(taskMapper.mapToTaskDtoList(tasks)).thenReturn(taskDtos);
        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/tasks"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(taskDtos)));
    }


    @Test
    void getTask() throws Exception{
        // Given
        TaskDTo taskDto = new TaskDTo(1L, "test title", "test_content");
        when(taskMapper.mapToTaskDto(any(Task.class))).thenReturn(taskDto);
        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/tasks/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("test title"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content").value("test_content"));



    }

    @Test
    void deleteTask() throws Exception{
        // Given
        doNothing().when(service).delete(1L);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/tasks/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateTask() throws Exception {
        // Given
        TaskDTo taskDto = new TaskDTo(1L, "Test Task 1", "Test Content 1");
        when(taskMapper.mapToTask(any(TaskDTo.class))).thenReturn(new Task());
        when(service.saveTask(any(Task.class))).thenReturn(new Task());

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createTask() throws  Exception{
        // Given
        TaskDTo taskDto = new TaskDTo(1L, "Test Task 1", "Test Content 1");
        when(taskMapper.mapToTask(any(TaskDTo.class))).thenReturn(new Task());

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}