package com.create.task_tracker.Mappers;

import com.create.task_tracker.Domain.DTO.TaskDto;
import com.create.task_tracker.Domain.DTO.TaskListDto;
import com.create.task_tracker.Domain.Model.TaskListModel;

public interface TaskListMapper {


    TaskListModel fromDto(TaskListDto taskListDto);

    TaskListDto toDto(TaskListModel taskListModel);
}
