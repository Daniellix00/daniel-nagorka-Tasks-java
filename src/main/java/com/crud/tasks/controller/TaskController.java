package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDTo;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/tasks")
public class TaskController {
    private final DbService service;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskController(DbService service, TaskMapper taskMapper) {
        this.service = service;
        this.taskMapper = taskMapper;
    }
    @GetMapping(value =  "{taskId}")
    public TaskDTo getTaskById(@PathVariable Long id){
        Optional<Task> taskOptional = service.getTaskById(id);
        return taskOptional.map(taskMapper::mapToTaskDto).orElse(null);
    }
    @GetMapping
    public List<TaskDTo> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return taskMapper.mapToTaskDtoList(tasks);
    }
@GetMapping(value =  "{taskId}")
    public TaskDTo getTask( @PathVariable Long taskId) {
        return new TaskDTo(1L, "test title", "test_content");
    }
@DeleteMapping
    public void deleteTask(Long taskId) {

    }
@PutMapping
    public TaskDTo updateTask(TaskDTo taskDto) {
        return new TaskDTo(1L, "Edited test title", "Test content");
    }
@PostMapping
    public void createTask(TaskDTo taskDto) {

    }
}
