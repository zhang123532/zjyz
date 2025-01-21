package com.zjyz.common.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MyBizException extends RuntimeException {
    private String errorCode;
    private String errorMessage;

    public MyBizException() {
        super();
    }

    public MyBizException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
}
