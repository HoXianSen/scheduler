package com.hxs.scheduler.service.errcode;

import com.hxs.scheduler.common.bean.ErrCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TaskServiceErrCode implements ErrCode {
    AddTaskFail(200, "添加Task失败"),
    ;
    private final int code;
    private final String msg;
}
