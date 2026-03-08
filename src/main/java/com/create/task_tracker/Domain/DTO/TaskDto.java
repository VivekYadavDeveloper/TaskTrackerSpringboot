package com.create.task_tracker.Domain.DTO;

import com.create.task_tracker.Domain.TaskPriorityEnum;
import com.create.task_tracker.Domain.TaskStatusEnum;

import java.time.LocalDateTime;
import java.util.UUID;

public record TaskDto(
        UUID id,
        String title,
        String description,
        LocalDateTime dueDate,
        TaskPriorityEnum priority,
        TaskStatusEnum status

) {
}
