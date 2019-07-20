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

    public ResMsg(ResCode resCode) {
        this.code = resCode.getCode();
        this.msg = resCode.getMsg();
    }

    public ResMsg(ResCode resCode, Object data) {
        this(resCode);
        this.data = data;
    }

    public static ResMsg normalFail(String reason) {
        ResMsg msg = new ResMsg(CommonCode.Fail);
        msg.setMsg(reason);
        return msg;
    }
}
