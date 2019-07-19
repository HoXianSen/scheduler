package com.hxs.scheduler.service;

import com.hxs.scheduler.entity.Task;
import com.hxs.scheduler.job.TaskJob;
import org.quartz.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class QuartzSchedulerService {
    @Resource
    private Scheduler scheduler;


    public void scheduleJob(Task task) throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(TaskJob.class)
                .withIdentity(String.format("%d_%s", task.getId(), task.getName()), task.getGroup())
                .withDescription(task.getDescription()).build();
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(String.format("%d_%s_[%s]", task.getId(), task.getName(), task.getCron()), task.getGroup())
                .withSchedule(CronScheduleBuilder.cronSchedule(task.getCron())).build();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
