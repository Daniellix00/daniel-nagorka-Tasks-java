package com.crud.tasks.facade;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDTo;
import com.crud.tasks.mapper.TaskMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskMapperTest {
    @Test
    void mapToTask() {
        // Given
        TaskDTo taskDto = new TaskDTo(1L, "Test Task", "Test Content");
        TaskMapper taskMapper = new TaskMapper();

        // When
        Task task = taskMapper.mapToTask(taskDto);

        // Then
        assertEquals(taskDto.getId(), task.getId());
        assertEquals(taskDto.getTitle(), task.getTitle());
        assertEquals(taskDto.getContent(), task.getContent());
    }

    @Test
    void mapToTaskDto() {
        // Given
        Task task = new Task(1L, "Test Task", "Test Content");
        TaskMapper taskMapper = new TaskMapper();

        // When
        TaskDTo taskDto = taskMapper.mapToTaskDto(task);

        // Then
        assertEquals(task.getId(), taskDto.getId());
        assertEquals(task.getTitle(), taskDto.getTitle());
        assertEquals(task.getContent(), taskDto.getContent());
    }

    @Test
    void mapToTaskDtoList() {
        // Given
        TaskMapper taskMapper = new TaskMapper();
        List<Task> taskList = List.of(
                new Task(1L, "Test Task 1", "Test Content 1"),
                new Task(2L, "Test Task 2", "Test Content 2"),
                new Task(3L, "Test Task 3", "Test Content 3")
        );

        // When
        List<TaskDTo> taskDtoList = taskMapper.mapToTaskDtoList(taskList);

        // Then
        assertEquals(taskList.size(), taskDtoList.size());
        for (int i = 0; i < taskList.size(); i++) {
            assertEquals(taskList.get(i).getId(), taskDtoList.get(i).getId());
            assertEquals(taskList.get(i).getTitle(), taskDtoList.get(i).getTitle());
            assertEquals(taskList.get(i).getContent(), taskDtoList.get(i).getContent());
        }
    }
}
