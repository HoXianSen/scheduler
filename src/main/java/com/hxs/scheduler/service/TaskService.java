package com.hxs.scheduler.service;

import com.hxs.scheduler.common.ServiceException;
import com.hxs.scheduler.entity.Task;
import com.hxs.scheduler.repository.TaskRepository;
import org.quartz.SchedulerException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

import static com.hxs.scheduler.service.errcode.TaskServiceErrCode.*;

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

    public void addTask(Task task) {
        taskRepository.saveAndFlush(task);
        try {
            schedulerService.scheduleJob(task);
        } catch (SchedulerException e) {
            throw new ServiceException(AddTaskFail, e);
        }
    }

    public void startTask(Integer id) {
        Optional<Task> task = taskRepository.findById(id);
        task.ifPresent(t->schedulerService.resumeJob(t));
    }

    public void pauseTask(int id) {
        Optional<Task> task = taskRepository.findById(id);
        task.ifPresent(t -> schedulerService.pauseJob(t));
    }

    public void deleteTask(int id) {
        Optional<Task> task = taskRepository.findById(id);
        task.ifPresent(t -> {
            schedulerService.deleteJob(t);
            taskRepository.deleteById(id);
        });
    }
}
