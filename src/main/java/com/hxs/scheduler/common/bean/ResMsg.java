package com.hxs.scheduler.common.bean;

import com.google.common.collect.Lists;
import lombok.Data;

@Data
public class ResMsg {
    private boolean success;
    private String msg;
    private Object data;

    public ResMsg(boolean success) {
        this.success = success;
    }

    public static ResMsg fail(String reason) {
        ResMsg msg = new ResMsg(false);
        msg.setMsg(reason);
        return msg;
    }

    public static ResMsg ok() {
        return new ResMsg(true);
    }

    public static ResMsg ok(Object data) {
        ResMsg msg = new ResMsg(true);
        msg.setData(data);
        return msg;
    }

    public static ResMsg ok(Object... data) {
        ResMsg msg = new ResMsg(true);
        msg.setData(Lists.newArrayList(data));
        return msg;
    }
}
