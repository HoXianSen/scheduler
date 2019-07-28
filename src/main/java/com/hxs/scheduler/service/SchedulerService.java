package com.hxs.scheduler.service;

import com.hxs.scheduler.common.KeyConstant;
import com.hxs.scheduler.common.util.DateFormatHelper;
import com.hxs.scheduler.entity.Task;
import com.hxs.scheduler.job.TaskJob;
import com.hxs.scheduler.service.exception.SchedulerServiceException;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service("schedulerService")
public class SchedulerService {
    @Resource
    private Scheduler scheduler;
    @Resource
    private TaskService taskService;


    public void scheduleJob(Task task) throws SchedulerException {
        log.debug("开始scheduleJob，taskId={}", task.getId());
        JobDetail jobDetail = JobBuilder.newJob(TaskJob.class)
                .withIdentity(getJobKey(task))
                .withDescription(task.getDescription())
                .usingJobData(KeyConstant.CMD, task.getCmd())
                .build();
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(getTriggerKey(task))
                .withSchedule(CronScheduleBuilder.cronSchedule(task.getCron())).build();
        Date nextFireTime = scheduler.scheduleJob(jobDetail, trigger);
        log.info("scheduleJob成功，jobKey={}，下次执行时间：{}", jobDetail.getKey(), DateFormatHelper.yMdHms(nextFireTime));
    }

    public void resumeJob(Task task) {
        try {
            scheduler.resumeJob(getJobKey(task));
        } catch (SchedulerException e) {
            throw new SchedulerServiceException("", e);
        }
    }

    public boolean pauseJob(Task task) {
        try {
            scheduler.pauseJob(getJobKey(task));
        } catch (SchedulerException e) {
            log.error("pauseJob error", e);
            return false;
        }
        return true;
    }

    public boolean deleteJob(Task task) {
        try {
            return scheduler.deleteJob(getJobKey(task));
        } catch (SchedulerException e) {
            log.error("deleteJob error", e);
            return false;
        }
    }

    public boolean deleteAllJob() {
        log.warn("删除所有Job！");
        List<Task> allTask = taskService.getAllTask();
        List<JobKey> jobKeys = allTask.stream()
                .map(this::getJobKey)
                .collect(Collectors.toList());
        try {
            return scheduler.deleteJobs(jobKeys);
        } catch (SchedulerException e) {
            log.error("deleteAllJob error", e);
            return false;
        }
    }

    public boolean pauseAllJob() {
        try {
            scheduler.pauseAll();
        } catch (SchedulerException e) {
            log.error("pauseAllJob error", e);
            return false;
        }
        return true;
    }


    private JobKey getJobKey(Task task) {
        return new JobKey(String.valueOf(task.getId()));
    }


    private TriggerKey getTriggerKey(Task task) {
        return new TriggerKey(String.valueOf(task.getId()));
    }

}
