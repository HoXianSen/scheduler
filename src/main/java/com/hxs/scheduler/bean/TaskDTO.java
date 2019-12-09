package com.hxs.scheduler.bean;

import lombok.Data;

import java.util.List;

@Data
public class TaskDTO {
    private int scriptId;
    private String taskName;
    private String cron;
    private List<Param> params;
}
