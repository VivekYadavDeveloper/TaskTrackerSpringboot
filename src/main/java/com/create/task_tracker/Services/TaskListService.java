package com.create.task_tracker.Services;

import com.create.task_tracker.Domain.Model.TaskListModel;

import java.util.List;

public interface TaskListService {
    List<TaskListModel> listTaskLists();
}
