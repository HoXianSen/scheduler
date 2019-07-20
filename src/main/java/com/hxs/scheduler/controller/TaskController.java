package com.hxs.scheduler.controller;

import com.hxs.scheduler.common.bean.ResMsg;
import com.hxs.scheduler.entity.Task;
import com.hxs.scheduler.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {
    @Resource
    private TaskService taskService;

    @GetMapping("/add")
    public ResMsg addTask(Task task) {
        log.debug("添加任务Task，task={}", task);
        try {
            taskService.addTask(task);
        } catch (Exception e) {
            log.error("添加Task异常", e);
            return ResMsg.normalFail(e.getMessage());
        }
        return ResMsg.SUCCESS;
    }
}
