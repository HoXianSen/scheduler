package com.hxs.scheduler.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Table(name = "task")
@Entity
@Data
public class Task implements Serializable {
  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  /**
   * 任务执行的命令
   */
  @Column(name = "cmd")
  private String cmd;

  /**
   * cron表达式
   */
  @Column(name = "cron")
  private String cron;

  /**
   * 任务名字
   */
  @Column(name = "task_name")
  private String taskName;

  /**
   * 脚本名字
   */
  @Column(name = "script_name")
  private String scriptName;

  /**
   * 任务描述
   */
  @Column(name = "description")
  private String description;

  
}