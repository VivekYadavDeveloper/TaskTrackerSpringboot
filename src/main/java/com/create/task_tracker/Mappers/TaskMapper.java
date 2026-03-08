package com.create.task_tracker.Mappers;

import com.create.task_tracker.Domain.DTO.TaskDto;
import com.create.task_tracker.Domain.Model.TaskModel;

public interface TaskMapper {

    TaskModel fromDto(TaskDto taskDto);

    TaskDto toDto(TaskModel taskModel);
}
