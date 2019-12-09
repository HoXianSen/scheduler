package com.hxs.scheduler.controller;

import com.google.common.base.Joiner;
import com.hxs.scheduler.bean.ScriptTask;
import com.hxs.scheduler.bean.ScriptVO;
import com.hxs.scheduler.bean.TaskDTO;
import com.hxs.scheduler.bean.ResMsg;
import com.hxs.scheduler.config.ParamsEditor;
import com.hxs.scheduler.entity.Script;
import com.hxs.scheduler.mapper.ScriptMapper;
import com.hxs.scheduler.service.SchedulerService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/task")
public class TaskController {
    @Resource
    private ScriptMapper scriptMapper;
    @Resource
    private SchedulerService schedulerService;

    @GetMapping("/create/{id}")
    public ModelAndView create(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView("task/create");
        Script script = scriptMapper.selectById(id);
        ScriptVO scriptVO = new ScriptVO(script);
        modelAndView.addObject("scriptVO", scriptVO);
        return modelAndView;
    }

    @PostMapping("/create")
    @ResponseBody
    public ResMsg create(TaskDTO taskDTO) {
        int scriptId = taskDTO.getScriptId();
        Script script = scriptMapper.selectById(scriptId);
        if (script == null) {
            return ResMsg.fail("错误的ScriptId");
        }
        ScriptTask task = new ScriptTask();
        task.setScriptId(String.valueOf(scriptId));
        task.setTaskName(taskDTO.getTaskName());
        String params = Joiner.on(" ").join(taskDTO.getParams());
        String cmd = Joiner.on(" ").join("python", script.getName(), params);
        task.setCmd(cmd);
        task.setCron(taskDTO.getCron());
        try {
            schedulerService.scheduleTask(task);
        } catch (SchedulerException e) {
            log.error("创建任务失败", e);
            return ResMsg.fail("创建任务失败");
        }
        return ResMsg.ok();
    }

    @GetMapping("/pause/{id}")
    public ResMsg pauseTask(@PathVariable @NotNull Integer id) {
        return ResMsg.ok();
    }

    @GetMapping("/start/{id}")
    public ResMsg startTask(@PathVariable @NotNull Integer id) {
        return ResMsg.ok();
    }


    @GetMapping("/delete/{id}")
    public ResMsg deleteTask(@PathVariable @NotNull Integer id) {
        return ResMsg.ok();
    }

    @GetMapping("/all")
    public ResMsg getAll() {
        return ResMsg.ok();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(List.class, "params", new ParamsEditor());
    }
}
