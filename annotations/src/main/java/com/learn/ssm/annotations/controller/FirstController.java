package com.learn.ssm.annotations.controller;

import com.learn.ssm.annotations.confs.CustomException;
import com.learn.ssm.annotations.model.Person;
import com.learn.ssm.annotations.model.Result;
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
    public Person needAdviceController(@PathVariable("id") int id) throws Exception {
        if (id % 2 != 0){
            throw  new Exception("needAdviceController throw Exception");
        }
        if (id % 4 != 0){
            throw new CustomException("1000", "needAdviceController throw customException");
        }
        return new Person("xiaoxiao", 24);
    }

    @RequestMapping(value = "/needAdviceResult", produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result needAdviceResult(){
        return new Result();
    }
}
