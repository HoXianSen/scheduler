package com.hxs.scheduler.common.bean;

public enum CommonCode implements ResCode {
    UnknownError(-1, ""),
    Success(0, ""),
    Fail(1, ""),
    ;
    private int code;
    private String msg;

    CommonCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
