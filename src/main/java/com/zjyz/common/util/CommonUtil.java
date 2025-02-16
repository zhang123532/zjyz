package com.zjyz.common.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonUtil {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddhhmmss");


    public static String getCid() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        //return request.getHeader("cid");
        return "cbaa36e1df544f00a37337c34cbc7083";
    }

    public static String createUuid() {
        return LocalDateTime.now().format(DATE_TIME_FORMATTER) + RandomStringUtils.randomAlphanumeric(2);
    }

    public static String convertLocalDateTimeToString(LocalDateTime dateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return dateTime.format(formatter);
    }

}
