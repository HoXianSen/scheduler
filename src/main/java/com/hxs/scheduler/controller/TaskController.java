package com.hxs.scheduler.controller;

import com.hxs.scheduler.common.bean.ResMsg;
import com.hxs.scheduler.entity.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {
//    @Resource
//    private TaskService taskService;

    @PutMapping("/add")
    public ResMsg addTask(@RequestBody @Validated Task task) {
        log.debug("添加任务Task，task={}", task);
//        taskService.addTask(task);
        return ResMsg.ok();
    }

    @GetMapping("/pause/{id}")
    public ResMsg pauseTask(@PathVariable @NotNull Integer id) {
//        taskService.pauseTask(id);
        return ResMsg.ok();
    }

    @GetMapping("/start/{id}")
    public ResMsg startTask(@PathVariable @NotNull Integer id) {
//        taskService.startTask(id);
        return ResMsg.ok();
    }


    @GetMapping("/delete/{id}")
    public ResMsg deleteTask(@PathVariable @NotNull Integer id) {
//        taskService.deleteTask(id);
        return ResMsg.ok();
    }

    @GetMapping("/all")
    public ResMsg getAll() {
//        List<Task> allTask = taskService.getAllTask();

        return ResMsg.ok();
    }
}
