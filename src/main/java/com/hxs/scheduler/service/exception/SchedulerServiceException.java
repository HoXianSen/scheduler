package com.hxs.scheduler.service.exception;

public class SchedulerServiceException extends RuntimeException {
    public SchedulerServiceException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public SchedulerServiceException(String msg) {
        super(msg);
    }
}
