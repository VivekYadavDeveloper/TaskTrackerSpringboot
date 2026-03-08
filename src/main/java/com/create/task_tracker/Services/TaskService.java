package com.create.task_tracker.Services;

import com.create.task_tracker.Domain.Model.TaskModel;
import org.springframework.scheduling.config.Task;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskService {
    List<TaskModel> listTasks(UUID taskListId);

    TaskModel createTask(UUID taskListId, TaskModel taskModel);

    Optional<TaskModel> getTaskById(UUID taskListId, UUID taskId);

    TaskModel updateTask(UUID taskListId, UUID taskId, TaskModel taskModel);

    void deleteTask(UUID taskListId, UUID taskId);

}
/*2:24:12*/