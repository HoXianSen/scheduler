package com.hxs.scheduler.entity;

import java.io.Serializable;
import lombok.Data;

@Data
public class Task implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  private Integer id;

  /**
   * 任务执行的命令
   */
  private String cmd;

  /**
   * cron表达式
   */
  private String cron;

  /**
   * 任务名字
   */
  private String taskName;

  /**
   * 任务描述
   */
  private String description;

}