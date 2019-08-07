package com.hxs.scheduler.controller;

import com.hxs.scheduler.common.bean.ResMsg;
import com.hxs.scheduler.entity.Task;
import com.hxs.scheduler.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {
    @Resource
    private TaskService taskService;

    @PutMapping("/add")
    public ResMsg addTask(@RequestBody @Validated Task task) {
        log.debug("添加任务Task，task={}", task);
        taskService.addTask(task);
        return ResMsg.SUCCESS;
    }

    @GetMapping("/pause/{id}")
    public ResMsg pauseTask(@PathVariable @NotNull Integer id) {
        taskService.pauseTask(id);
        return ResMsg.SUCCESS;
    }

    @GetMapping("/start/{id}")
    public ResMsg startTask(@PathVariable @NotNull Integer id) {
        taskService.startTask(id);
        return ResMsg.SUCCESS;
    }


    @GetMapping("/delete/{id}")
    public ResMsg deleteTask(@PathVariable @NotNull Integer id) {
        taskService.deleteTask(id);
        return ResMsg.SUCCESS;
    }

    @GetMapping("/all")
    public ResMsg getAll() {
        List<Task> allTask = taskService.getAllTask();

        return ResMsg.success(allTask);
    }
}
