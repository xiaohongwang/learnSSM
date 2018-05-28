package com.learn.ssm.annotations.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/first")
public class FirstController {

    @RequestMapping(value = "/needAdviceController/{id}", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String needAdviceController(@PathVariable("id") int id) throws Exception {
        if (id % 2 != 0){
            throw  new Exception("needAdviceController throw Exception");
        }
        return "true";
    }
}
