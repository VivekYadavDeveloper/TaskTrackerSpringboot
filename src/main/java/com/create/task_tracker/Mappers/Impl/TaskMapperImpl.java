package com.create.task_tracker.Mappers.Impl;

import com.create.task_tracker.Domain.DTO.TaskDto;
import com.create.task_tracker.Mappers.TaskMapper;
import com.create.task_tracker.Domain.Model.TaskModel;
import org.springframework.stereotype.Component;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public TaskModel fromDto(TaskDto taskDto) {
        return new TaskModel(
                taskDto.id(),
                taskDto.title(),
                taskDto.description(),
                taskDto.dueDate(),
                taskDto.status(),
                taskDto.priority(),
                null,
                null,
                null

        );
    }

    @Override
    public TaskDto toDto(TaskModel taskModel) {
        return new TaskDto(
                taskModel.getId(),
                taskModel.getTitle(),
                taskModel.getDescription(),
                taskModel.getDueDate(),
                taskModel.getPriority(),
                taskModel.getStatus()
        );
    }
}
