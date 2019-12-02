package com.hxs.scheduler.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Script implements Serializable {
    private Integer id;
    private String name;
    private String path;
    private String params;
}
