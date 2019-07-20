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
                .withDescription("").build();
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(CronScheduleBuilder.cronSchedule(task.getCron())).build();
        scheduler.scheduleJob(jobDetail, trigger);
    }
}
