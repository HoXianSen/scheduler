package com.hxs.scheduler.mgr;

import org.quartz.Scheduler;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class QuartzTaskManager {
    @Resource
    private SchedulerFactoryBean schedulerFactoryBean;
}
