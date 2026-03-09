package com.create.task_tracker.Services.Imple;

import com.create.task_tracker.Domain.Model.TaskListModel;
import com.create.task_tracker.Repositories.TaskListRepository;
import com.create.task_tracker.Services.TaskListService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskListServiceImpl implements TaskListService {
    private final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskListModel> listTaskLists() {
        return taskListRepository.findAll();
    }

    @Override
    public TaskListModel createTaskList(TaskListModel taskListModel) {

        if (taskListModel.getId() != null) {
            throw new IllegalArgumentException("TaskList Already has an ID");
        }
        if (taskListModel.getTitle() == null || taskListModel.getTitle().isBlank()) {
            throw new IllegalArgumentException("TaskList Title cannot be null or blank");
        }
        LocalDateTime now = LocalDateTime.now();
        return taskListRepository.save(new TaskListModel(
                null,
                taskListModel.getTitle(),
                taskListModel.getDescription(),
                null,
                now,
                now
        ));

    }

    @Override
    public Optional<TaskListModel> getTaskListById(UUID id) {
        return taskListRepository.findById(id);
    }

    @Transactional
    @Override
    public TaskListModel updateTaskList(UUID taskListId, TaskListModel taskListModel) {
        if (taskListModel.getId() == null) {
            throw new IllegalArgumentException("TaskList ID cannot be null");
        }
        if (!Objects.equals(taskListId, taskListModel.getId())) {
            throw new IllegalArgumentException("TaskList ID in path and body must match");
        }
        TaskListModel existingTaskList = taskListRepository.findById(taskListId).orElseThrow(()
                -> new IllegalArgumentException("TaskList ID not found"));
        existingTaskList.setTitle(taskListModel.getTitle());
        existingTaskList.setDescription(taskListModel.getDescription());
        existingTaskList.setUpdated(LocalDateTime.now());
        return taskListRepository.save(existingTaskList);
    }

    @Override
    public void deleteTaskList(UUID taskListId) {
        taskListRepository.deleteById(taskListId);
    }
}
