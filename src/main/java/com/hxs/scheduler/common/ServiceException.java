package com.hxs.scheduler.common;

import com.hxs.scheduler.common.bean.ResCode;

public class ServiceException extends RuntimeException {
    private ResCode resCode;

    public ServiceException(ResCode resCode) {
        super(resCode.getMsg());
    }

    public ServiceException(ResCode resCode, Throwable cause) {
        super(resCode.getMsg(), cause);
    }

    public ResCode getResCode() {
        return resCode;
    }
}
