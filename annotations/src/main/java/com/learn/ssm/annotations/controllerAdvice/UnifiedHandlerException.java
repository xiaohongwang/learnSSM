package com.learn.ssm.annotations.controllerAdvice;

import com.learn.ssm.annotations.confs.CustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 */
//拦截路径
//@ControllerAdvice(basePackages = {"com.learn.ssm.annotations.controller"})
//拦截注解 更精确
@ControllerAdvice(annotations = {Controller.class})
public class UnifiedHandlerException {

    private static final Logger log = LoggerFactory.getLogger(UnifiedHandlerException.class);
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String handlerException(Exception e) {
        if (e instanceof CustomException){
            CustomException customException = (CustomException)e;
            return "Code: " + customException.getCode() + " ; mesage: " + customException.getMessage();
        }
        log.error(e.getMessage());
        return "handlerException throw Exception";
    }
}
