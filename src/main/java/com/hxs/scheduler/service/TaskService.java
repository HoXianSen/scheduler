package com.hxs.scheduler.service;

import com.hxs.scheduler.entity.Task;
import com.hxs.scheduler.repository.TaskRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TaskService {
    @Resource
    private TaskRepository taskRepository;

    public List<Task> getAllTask() {
        List<Task> allTask = taskRepository.findAll();
        return allTask;
    }

    public void addTask(Task task){
        taskRepository.saveAndFlush(task);
    }
}
