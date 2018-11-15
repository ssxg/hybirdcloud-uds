package com.lenovo.ecr.hybirdcloud.uds.facede.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Map baseErrorHandler(Exception e) throws Exception {
        Map map = new HashMap();
        String r = e.getMessage();
        System.out.print(r);
        map.put("code",100);
        map.put("message",r);
        return map;
    }
}
