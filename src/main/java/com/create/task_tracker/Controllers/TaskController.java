package com.create.task_tracker.Controllers;

import com.create.task_tracker.Domain.DTO.TaskDto;
import com.create.task_tracker.Domain.Model.TaskModel;
import com.create.task_tracker.Mappers.TaskMapper;
import com.create.task_tracker.Services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/task-lists/{task_list_id}/tasks")
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;


    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }


    @GetMapping
    public List<TaskDto> getTaskById(@PathVariable UUID task_list_id) {
        return taskService.listTasks(task_list_id).stream().map(taskMapper::toDto).toList();
    }


    @PostMapping
    public TaskDto createTask(@PathVariable UUID task_list_id, @RequestBody TaskDto taskDto) {
        TaskModel createTask = taskService.createTask(task_list_id, taskMapper.fromDto(taskDto));

        return taskMapper.toDto(createTask);

    }
}
