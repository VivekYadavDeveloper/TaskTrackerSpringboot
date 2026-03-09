package com.create.task_tracker.Controllers;

import com.create.task_tracker.Domain.DTO.TaskDto;
import com.create.task_tracker.Domain.DTO.TaskListDto;
import com.create.task_tracker.Domain.Model.TaskModel;
import com.create.task_tracker.Mappers.TaskMapper;
import com.create.task_tracker.Services.TaskService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @GetMapping("/{task_id}")
    public Optional<TaskDto> getTaskById(@PathVariable UUID task_list_id, @PathVariable UUID task_id) {
        return taskService.getTaskById(task_list_id, task_id).map(taskMapper::toDto);
    }


    @PostMapping
    public TaskDto createTask(@PathVariable UUID task_list_id, @RequestBody TaskDto taskDto) {
        TaskModel createTask = taskService.createTask(task_list_id, taskMapper.fromDto(taskDto));

        return taskMapper.toDto(createTask);
    }

    @PutMapping("/{task_id}")
    public TaskDto updateTask(@PathVariable UUID task_list_id, @PathVariable UUID task_id, @RequestBody TaskDto taskDto) {
        TaskModel updateTask = taskService.updateTask(task_list_id, task_id, taskMapper.fromDto(taskDto));
        return taskMapper.toDto(updateTask);
    }

    @DeleteMapping("/{task_id}")
    public void deleteTask(@PathVariable UUID task_list_id, @PathVariable UUID task_id) {
        taskService.deleteTask(task_list_id, task_id);

    }
}