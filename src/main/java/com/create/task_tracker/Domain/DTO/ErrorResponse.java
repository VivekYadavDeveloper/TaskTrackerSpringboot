package com.create.task_tracker.Domain.DTO;

public record ErrorResponse(
        int status,
        String message,
        String details
) {
}
