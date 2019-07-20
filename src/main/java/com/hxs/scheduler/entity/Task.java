package com.hxs.scheduler.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Table(name = "task")
@Data
@Entity
public class Task implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, nullable = false)
    private Integer id;

    /**
     * 任务执行的命令
     */
    @NotNull
    @Column(name = "cmd")
    private String cmd;

    /**
     * cron表达式
     */
    @NotNull
    @Column(name = "cron")
    private String cron;

    /**
     * 名字
     */
    @NotNull
    @Column(name = "name")
    private String name;

    /**
     * group可以代表哪个脚本的任务
     */
    @NotNull
    @Column(name = "group")
    private String group;

    /**
     * 任务描述
     */
    @Column(name = "description")
    private String description;


}