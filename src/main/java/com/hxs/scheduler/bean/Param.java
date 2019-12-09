package com.hxs.scheduler.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Param {
    private String name;
    private String value;

    @Override
    public String toString() {
        return name + "=" + value;
    }
}