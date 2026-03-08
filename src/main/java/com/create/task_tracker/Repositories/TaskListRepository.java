package com.create.task_tracker.Repositories;

import com.create.task_tracker.Domain.Model.TaskListModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaskListRepository extends JpaRepository<TaskListModel, UUID> {




}
