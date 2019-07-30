package com.hxs.scheduler.config;

import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class BeanConfiguration {

    @Bean
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactory) {
        return schedulerFactory.getScheduler();
    }
}
