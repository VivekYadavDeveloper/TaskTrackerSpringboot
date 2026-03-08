package com.create.task_tracker.Services.Imple;

import com.create.task_tracker.Domain.Model.TaskListModel;
import com.create.task_tracker.Repositories.TaskListRepository;
import com.create.task_tracker.Services.TaskListService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskListServiceImpl implements TaskListService {
private  final TaskListRepository taskListRepository;

    public TaskListServiceImpl(TaskListRepository taskListRepository) {
        this.taskListRepository = taskListRepository;
    }

    @Override
    public List<TaskListModel> listTaskLists(){
        return  taskListRepository.findAll();
    }
}
