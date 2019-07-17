package com.hxs.scheduler.startup;

import com.hxs.scheduler.entity.Task;
import com.hxs.scheduler.service.TaskService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class TaskStartup implements CommandLineRunner {
    @Resource
    private TaskService taskService;

    @Override
    public void run(String... args) throws Exception {
        List<Task> allTask = taskService.getAllTask();
        allTask.forEach(task -> {
            
        });
    }
}
