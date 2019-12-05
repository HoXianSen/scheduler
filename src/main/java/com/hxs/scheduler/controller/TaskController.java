package com.hxs.scheduler.controller;

import com.hxs.scheduler.bean.ScriptVO;
import com.hxs.scheduler.common.bean.ResMsg;
import com.hxs.scheduler.entity.Script;
import com.hxs.scheduler.entity.Task;
import com.hxs.scheduler.mapper.ScriptMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {
    //    @Resource
//    private TaskService taskService;
    @Resource
    private ScriptMapper scriptMapper;

    @GetMapping("/create/{id}")
    public ModelAndView create(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("task/create");
        Script script = scriptMapper.selectById(id);
        ScriptVO scriptVO = new ScriptVO(script);
        modelAndView.addObject("scriptVO", scriptVO);
        return modelAndView;
    }

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
