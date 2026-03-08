package com.create.task_tracker.Services.Imple;

import com.create.task_tracker.Domain.Model.TaskListModel;
import com.create.task_tracker.Domain.Model.TaskModel;
import com.create.task_tracker.Domain.TaskPriorityEnum;
import com.create.task_tracker.Domain.TaskStatusEnum;
import com.create.task_tracker.Repositories.TaskListRepository;
import com.create.task_tracker.Repositories.TaskRepository;
import com.create.task_tracker.Services.TaskService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final TaskListRepository taskListRepository;

    public TaskServiceImpl(TaskRepository taskRepository, TaskListRepository taskListRepository) {
        this.taskRepository = taskRepository;

        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskModel> listTasks(UUID taskListId) {
        return taskRepository.findByTaskListId(taskListId);
    }

    @Override
    public TaskModel createTask(UUID taskListId, TaskModel taskModel) {

        if (taskModel.getId() != null) {
            throw new IllegalArgumentException("TaskList Already has an ID");
        }
        if (taskModel.getTitle() == null || taskModel.getTitle().isBlank()) {
            throw new IllegalArgumentException("TaskList Title cannot be null or blank");
        }

        TaskPriorityEnum taskPriorityEnum = Optional.ofNullable(taskModel.getPriority()).orElse(TaskPriorityEnum.MEDIUM);
        TaskStatusEnum taskStatusEnum = TaskStatusEnum.OPEN;

        TaskListModel taskListModel = taskListRepository.findById(taskListId).orElseThrow(() -> new IllegalArgumentException("TaskList with ID " + taskListId + " does not exist"));
        LocalDateTime now = LocalDateTime.now();


        TaskModel taskToSave = new TaskModel(
                null,
                taskModel.getTitle(),
                taskModel.getDescription(),
                taskModel.getDueDate(),
                taskStatusEnum,
                taskPriorityEnum,
                taskListModel,
                now,
                now
        );
        return taskRepository.save(taskToSave);
    }

    @Override
    public Optional<TaskModel> getTaskById(UUID taskListId, UUID taskId) {
        return taskRepository.findByTaskListIdAndId(taskListId, taskId);
    }

    @Override
    public TaskModel updateTask(UUID taskListId, UUID taskId, TaskModel taskModel) {
        return null;
    }

    @Override
    public void deleteTask(UUID taskListId, UUID taskId) {

    }
}
