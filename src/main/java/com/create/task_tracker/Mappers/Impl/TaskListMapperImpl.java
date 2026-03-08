package com.create.task_tracker.Mappers.Impl;

import com.create.task_tracker.Domain.DTO.TaskListDto;
import com.create.task_tracker.Mappers.TaskListMapper;
import com.create.task_tracker.Mappers.TaskMapper;
import com.create.task_tracker.Domain.Model.TaskListModel;
import com.create.task_tracker.Domain.Model.TaskModel;
import com.create.task_tracker.Domain.TaskStatusEnum;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskListMapperImpl implements TaskListMapper {
    private final TaskMapper taskMapper;

    public TaskListMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public TaskListModel fromDto(TaskListDto taskListDto) {
        return new TaskListModel(
                taskListDto.id(),
                taskListDto.title(),
                taskListDto.description(),
                Optional.ofNullable(taskListDto.tasks())
                        .map(tasks -> tasks.stream()
                                .map(taskMapper::fromDto).toList())
                        .orElse(null),
                null,
                null
        );
    }

    @Override
    public TaskListDto toDto(TaskListModel taskListModel) {
        return new TaskListDto(
                taskListModel.getId(),
                taskListModel.getTitle(),
                taskListModel.getDescription(),
                Optional.ofNullable(taskListModel.getTasks())
                        .map(List::size).orElse(0),
                calculateProgress(taskListModel.getTasks()), Optional.ofNullable(taskListModel.getTasks())
                .map(tasks -> tasks.stream()
                        .map(taskMapper::toDto).toList())
                .orElse(null)
        );
    }

    private Double calculateProgress(List<TaskModel> task) {
        if (null == task) {
            return null;
        }
        long closedTasksCount = task.stream().filter(tasks -> TaskStatusEnum.CLOSED == tasks.getStatus()).count();
        return (double) closedTasksCount / task.size();
    }
}
