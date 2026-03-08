package com.create.task_tracker.Controllers;

import com.create.task_tracker.Domain.DTO.TaskListDto;
import com.create.task_tracker.Domain.Model.TaskListModel;
import com.create.task_tracker.Mappers.TaskListMapper;
import com.create.task_tracker.Services.TaskListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/task-lists")
public class TaskListController {

    private final TaskListService taskListService;
    private final TaskListMapper taskListMapper;

    public TaskListController(TaskListService taskListService, TaskListMapper taskListMapper) {
        this.taskListService = taskListService;
        this.taskListMapper = taskListMapper;
    }

    @GetMapping("")
    public List<TaskListDto> listTaskLists() {
        return taskListService.listTaskLists().stream()
                .map(taskListMapper::toDto)
                .toList();
    }

    @GetMapping("/{task_list_id}")
    public Optional<TaskListDto> getTaskListById(@PathVariable UUID task_list_id) {
        return taskListService.getTaskListById(task_list_id).map(taskListMapper::toDto);
    }

    @PostMapping
    public TaskListDto createTaskList(@RequestBody TaskListDto taskListDto) {
        TaskListModel createTaskList = taskListService.createTaskList(taskListMapper.fromDto(taskListDto));
        return taskListMapper.toDto(createTaskList);
    }

    @PutMapping("/{task_list_id}")
    public TaskListDto updateTaskList(@PathVariable UUID task_list_id, @RequestBody TaskListDto taskListDto) {
        TaskListModel updateTaskList = taskListService.updateTaskList(task_list_id, taskListMapper.fromDto(taskListDto));
        return taskListMapper.toDto(updateTaskList);
    }

    @DeleteMapping("/{task_list_id}")
    public void deleteTaskList(@PathVariable UUID task_list_id) {
        taskListService.deleteTaskList(task_list_id);
    }

}
