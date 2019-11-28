package com.hxs.scheduler.bean;

import lombok.Data;

import java.util.List;

@Data
public class ScriptParams {
    private List<Param> params;

    @Data
    public static class Param {
        private String name;
        private String defValue;
    }
}
