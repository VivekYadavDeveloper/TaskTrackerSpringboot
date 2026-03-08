package com.create.task_tracker.Repositories;

import com.create.task_tracker.Domain.Model.TaskListModel;
import com.create.task_tracker.Domain.Model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, UUID> {

    List<TaskModel> findByTaskListId(UUID taskListId);
    Optional<TaskModel> findByTaskListIdAndId(UUID taskListId, UUID taskId);


}
