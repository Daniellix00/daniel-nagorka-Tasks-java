package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDTo;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@CrossOrigin("*")
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
    //@GetMapping(value =  "{taskId}")
   // public ResponseEntity<TaskDTo> getTask(@PathVariable Long taskId) throws  TaskNotFoundException {

      //  return ResponseEntity.ok(taskMapper.mapToTaskDto(service.getTask(taskId)));
   // }

    @GetMapping
    public ResponseEntity<List<TaskDTo>> getTasks() {
        List<Task> tasks = service.getAllTasks();
        return ResponseEntity.ok(taskMapper.mapToTaskDtoList(tasks));
   }
@GetMapping(value =  "{taskId}")
   public TaskDTo getTask( @PathVariable Long taskId) {
        return new TaskDTo(1L, "test title", "test_content");
   }
@DeleteMapping(value = "{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) throws TaskNotFoundException {
        service.delete(taskId);
return   ResponseEntity.ok().build();
    }
@PutMapping
    public ResponseEntity<TaskDTo> updateTask(@RequestBody TaskDTo taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        Task savedTask = service.saveTask(task);
        return ResponseEntity.ok(taskMapper.mapToTaskDto(savedTask));
    }
@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createTask(@RequestBody TaskDTo taskDto) {
        Task task = taskMapper.mapToTask(taskDto);
        service.saveTask(task);
        return ResponseEntity.ok().build();

    }
}
