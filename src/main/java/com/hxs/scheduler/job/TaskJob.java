package com.hxs.scheduler.job;

import com.hxs.scheduler.common.KeyConstant;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TaskJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        String cmd = jobDataMap.getString(KeyConstant.CMD);
        System.out.println(cmd);
    }
}
