package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDTo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskMapper {

    public Task mapToTask(final TaskDTo taskDto) {
        return new Task(
                taskDto.getId(),
                taskDto.getTitle(),
                taskDto.getContent()
        );
    }

    public TaskDTo mapToTaskDto(final Task task) {
        return new TaskDTo(
                task.getId(),
                task.getTitle(),
                task.getContent()
        );
    }

    public List<TaskDTo> mapToTaskDtoList(final List<Task> taskList) {
        return taskList.stream()
                .map(this::mapToTaskDto)
                .toList();
    }
}
