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
    @Resource
    private SchedulerService schedulerService;

    public List<Task> getAllTask() {
        List<Task> allTask = taskRepository.findAll();
        return allTask;
    }

    public void addTask(Task task) throws Exception {
        Task savedTask = taskRepository.saveAndFlush(task);
        if (savedTask == null) {
            throw new Exception("task保存失败");
        }
        schedulerService.scheduleJob(task);
    }
}
