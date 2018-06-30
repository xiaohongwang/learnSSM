package com.learn.ssm.annotations.controller;

import com.learn.ssm.annotations.confs.CustomException;
import com.learn.ssm.annotations.model.Person;
import com.learn.ssm.annotations.model.Result;
import com.learn.ssm.annotations.service.UserInfoService;
import com.learn.ssm.annotations.service.UserService;
import com.learn.ssm.annotations.vo.UserInfoVo;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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


    @Resource
    private UserInfoService userInfoService;

    @Resource
    private UserService userService;

    @RequestMapping(value = "/updateUserInfo", method = RequestMethod.GET ,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result updateUserInfo( UserInfoVo userInfoVo) {

        userInfoService.updateUserInfo(userInfoVo);

        return new Result();
    }

}
