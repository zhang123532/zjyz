package com.zjyz.common.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class RetBaseParam {
    private boolean isSucceed;
    private String returnCode;
    private String errorMessage;
    private Object data;

    public RetBaseParam(Object o) {
        data = o;
    }
}
