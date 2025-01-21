package com.zjyz.common.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MyDatabaseException extends RuntimeException {
    private String errorCode;
    private String errorMessage;

    public MyDatabaseException() {
        super();
    }

    public MyDatabaseException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }
}
