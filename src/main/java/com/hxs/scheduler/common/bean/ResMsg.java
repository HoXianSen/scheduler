package com.hxs.scheduler.common.bean;

import lombok.Data;

@Data
public class ResMsg {
    public transient static final ResMsg SUCCESS = new ResMsg();
    public transient static final ResMsg UNKNOWN_ERROR = new ResMsg();
    private int code;
    private String msg;
    private Object data;

    public ResMsg() {
        this(CommonCode.Success);
    }

    public ResMsg(ErrCode errCode) {
        this.code = errCode.getCode();
        this.msg = errCode.getMsg();
    }

    public ResMsg(ErrCode errCode, Object data) {
        this(errCode);
        this.data = data;
    }

    public ResMsg(Object data) {
        this();
        this.data = data;
    }

    public static ResMsg normalFail(String reason) {
        ResMsg msg = new ResMsg(CommonCode.Fail);
        msg.setMsg(reason);
        return msg;
    }
}
