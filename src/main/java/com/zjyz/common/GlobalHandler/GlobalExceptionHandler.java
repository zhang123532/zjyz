package com.zjyz.common.GlobalHandler;


import com.zjyz.common.bean.RetBaseParam;
import com.zjyz.common.exception.MyBizException;
import com.zjyz.common.exception.MyDatabaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
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
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RetBaseParam handleAllException(Exception e) {
        RetBaseParam ret=new RetBaseParam();
        ret.setReturnCode("UNKN001");
        ret.setSucceed(false);
        ret.setErrorMessage("发生未知错误"+e.getMessage());
        log.error("e: ", e);
        return  ret;
    }
}
