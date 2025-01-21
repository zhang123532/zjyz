package com.zjyz.common.GlobalHandler;

import com.zjyz.common.annotation.ZeeController;
import com.zjyz.common.bean.RetBaseParam;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@ControllerAdvice
public class GlobalResponseHandler implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return returnType.getDeclaringClass().isAnnotationPresent(ZeeController.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof RetBaseParam) {
            ((RetBaseParam) body).setSucceed(true);
            if (StringUtils.hasText(((RetBaseParam) body).getReturnCode())) {
                ((RetBaseParam) body).setSucceed(false);
            }else {
                ((RetBaseParam) body).setReturnCode("SUC0000");
            }
            return body;
        }
        RetBaseParam retBaseParam=new RetBaseParam(body);
        retBaseParam.setSucceed(true);
        retBaseParam.setReturnCode("SUC0000");
        return retBaseParam;
    }
}
