package com.hxs.scheduler.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.hxs.scheduler.bean.Constant;
import com.hxs.scheduler.bean.ScriptTask;
import com.hxs.scheduler.job.TaskJob;
import com.hxs.scheduler.util.DateFormatHelper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Slf4j
@Service("schedulerService")
public class SchedulerService {
    @Resource
    private Scheduler scheduler;


    public void scheduleTask(ScriptTask task) throws SchedulerException {
        log.debug("开始scheduleJob，taskId={}", task.getScriptId());
        JobDetail jobDetail = JobBuilder.newJob(TaskJob.class)
                .withIdentity(getJobKey(task))
                .usingJobData(Constant.CMD, task.getCmd())
                .usingJobData(Constant.CRON, task.getCron())
                .build();
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(getTriggerKey(task))
                .withSchedule(CronScheduleBuilder.cronSchedule(task.getCron())).build();
        Date nextFireTime = null;
        nextFireTime = scheduler.scheduleJob(jobDetail, trigger);
        log.info("scheduleJob成功，jobKey={}，下次执行时间：{}", jobDetail.getKey(), DateFormatHelper.yMdHms(nextFireTime));
    }

    public Multimap getAllTask() throws SchedulerException {
        List<String> groups = scheduler.getJobGroupNames();
        Multimap<String, ScriptTask> tasks = ArrayListMultimap.create();
        for (String group : groups) {
            Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.jobGroupEquals(group));
            for (JobKey jobKey : jobKeys) {
                JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                ScriptTask task = new ScriptTask();
                task.setScriptId(jobKey.getGroup());
                task.setTaskName(jobKey.getName());
                task.setCron(jobDetail.getJobDataMap().getString(Constant.CRON));
                task.setCmd(jobDetail.getJobDataMap().getString(Constant.CMD));
                tasks.put(group, task);
            }
        }
        return tasks;
    }

    private JobKey getJobKey(ScriptTask task) {
        String group = task.getScriptId();
        String name = task.getTaskName();
        return new JobKey(name, group);
    }


    private TriggerKey getTriggerKey(ScriptTask task) {
        String group = task.getScriptId();
        String name = task.getTaskName();
        return new TriggerKey(name, group);
    }

}
