package com.hxs.scheduler.service.errcode;

import com.hxs.scheduler.common.bean.ErrCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServiceErrCode implements ErrCode {
    ResumeJobFail(100, "重启Job失败"),
    PauseJobFail(101, "暂停Job失败"),
    DeleteJobFail(102, "删除Job失败"),
    ScheduleJobFail(103, "调度Job失败"),
    ;
    private final int code;
    private final String msg;
}
