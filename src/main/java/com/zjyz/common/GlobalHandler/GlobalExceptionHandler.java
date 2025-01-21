package com.zjyz.common.GlobalHandler;


import com.zjyz.common.bean.RetBaseParam;
import com.zjyz.common.exception.MyBizException;
import com.zjyz.common.exception.MyDatabaseException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MyDatabaseException.class)
    @ResponseBody
    public RetBaseParam handleMyDatabaseException(MyDatabaseException e) {
        RetBaseParam ret=new RetBaseParam();
        ret.setReturnCode(e.getErrorCode());
        ret.setSucceed(false);
        ret.setErrorMessage(e.getErrorMessage());
        return  ret;
    }

    @ExceptionHandler(MyBizException.class)
    @ResponseBody
    public RetBaseParam handleMyDatabaseException(MyBizException e) {
        RetBaseParam ret=new RetBaseParam();
        ret.setReturnCode(e.getErrorCode());
        ret.setSucceed(false);
        ret.setErrorMessage(e.getErrorMessage());
        return  ret;
    }
}
