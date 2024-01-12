package com.crud.tasks.controller;

import com.crud.tasks.domain.TaskDTo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/v1/tasks")
public class TaskController {
    @GetMapping
    public List<TaskDTo> getTasks() {
        return new ArrayList<>();
    }
@GetMapping
    public TaskDTo getTask(Long taskId) {
        return new TaskDTo(1L, "test title", "test_content");
    }
@DeleteMapping
    public void deleteTask(Long taskId) {

    }
@PostMapping
    public TaskDTo updateTask(TaskDTo taskDto) {
        return new TaskDTo(1L, "Edited test title", "Test content");
    }
@PutMapping
    public void createTask(TaskDTo taskDto) {

    }
}
