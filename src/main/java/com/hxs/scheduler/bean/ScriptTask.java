package com.hxs.scheduler.bean;

import lombok.Data;

@Data
public class ScriptTask {
    private String scriptId;
    private String taskName;
    private String cron;
    private String cmd;
}
