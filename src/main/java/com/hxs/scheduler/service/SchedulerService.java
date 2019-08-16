package com.hxs.scheduler.service;

import com.hxs.scheduler.common.KeyConstant;
import com.hxs.scheduler.common.util.DateFormatHelper;
import com.hxs.scheduler.entity.Task;
import com.hxs.scheduler.job.TaskJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Service("schedulerService")
public class SchedulerService {
    @Resource
    private Scheduler scheduler;


    public void scheduleJob(Task task) throws SchedulerException {
        log.debug("开始scheduleJob，task={}", task);
        JobDetail jobDetail = JobBuilder.newJob(TaskJob.class)
                .withIdentity(String.format("%d_%s", task.getId(), task.getTaskName()), task.getTaskGroup())
                .withDescription(task.getDescription())
                .usingJobData(KeyConstant.ID, task.getId())
                .usingJobData(KeyConstant.CMD, task.getCmd())
                .build();
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(String.format("%d_%s", task.getId(), task.getTaskName()), task.getTaskGroup())
                .withSchedule(CronScheduleBuilder.cronSchedule(task.getCron())).build();
        Date nextFireTime = scheduler.scheduleJob(jobDetail, trigger);
        log.info("scheduleJob成功，task={}，下次执行时间：{}", task, DateFormatHelper.yMdHms(nextFireTime));
    }

    public void removeAllJobs() {
        log.warn("移除scheduler中所有job！");

    }

}
