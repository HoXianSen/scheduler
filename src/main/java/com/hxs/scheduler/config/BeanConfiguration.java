package com.hxs.scheduler.config;

import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.List;

@Configuration
public class BeanConfiguration {

    @Bean
    public Scheduler scheduler(SchedulerFactoryBean schedulerFactory) {
        return schedulerFactory.getScheduler();
    }
}
