package com.hxs.scheduler.common.exception;

import com.hxs.scheduler.common.bean.ErrCode;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {
    private final ErrCode errCode;

    public ServiceException(ErrCode code) {
        super(code.getMsg());
        this.errCode = code;
    }

    public ServiceException(ErrCode code, Throwable cause) {
        super(code.getMsg(), cause);
        this.errCode = code;
    }
}
