package com.hxs.scheduler.common;

import com.hxs.scheduler.common.bean.ErrCode;

public class ServiceException extends RuntimeException {
    private ErrCode errCode;

    public ServiceException(ErrCode errCode) {
        super(errCode.getMsg());
    }

    public ServiceException(ErrCode errCode, Throwable cause) {
        super(errCode.getMsg(), cause);
    }

    public ErrCode getErrCode() {
        return errCode;
    }
}
