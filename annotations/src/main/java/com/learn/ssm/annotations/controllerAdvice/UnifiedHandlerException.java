package com.learn.ssm.annotations.controllerAdvice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice(basePackages = {"com.learn.ssm.annotations.controller"})
public class UnifiedHandlerException {

    private static final Logger log = LoggerFactory.getLogger(UnifiedHandlerException.class);
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handlerException(Exception e) {
        log.error(e.getMessage());
        return "handlerException throw Exception";
    }
}
