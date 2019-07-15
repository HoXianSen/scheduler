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

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", insertable = false, nullable = false)
  private Integer id;

  /**
   * 命令
   */
  @Column(name = "cmd")
  private String cmd;

  /**
   * cron表达式
   */
  @Column(name = "cron")
  private String cron;

  
}