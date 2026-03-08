package com.create.task_tracker.Services;

import com.create.task_tracker.Domain.Model.TaskListModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TaskListService {
    List<TaskListModel> listTaskLists();

    TaskListModel createTaskList(TaskListModel taskListModel);

    Optional<TaskListModel> getTaskListById(UUID id);

    TaskListModel updateTaskList(UUID taskListId, TaskListModel taskListModel);

    void deleteTaskList(UUID taskListId);
}
