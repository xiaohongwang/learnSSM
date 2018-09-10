package com.learn.ssm.annotations.service.impl;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.learn.ssm.annotations.mapper.UserInfoMapper;
import com.learn.ssm.annotations.service.UserInfoService;
import com.learn.ssm.annotations.service.UserService;
import com.learn.ssm.annotations.vo.UserInfoVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    private static final Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);
    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private UserService userService;

    @Override
    @Transactional(value = "localTransactionManager")
    public void updateUserInfo(UserInfoVo userInfoVo){


        try {

            userInfoMapper.updateUserInfo(userInfoVo);

            userInfoVo.setUserScore(800.0);

            userInfoMapper.updateUserInfo(userInfoVo);


            userService.update(userInfoVo);
//            userInfoMapper.insertUserInfo(userInfoVo);
        } catch (Exception e){
            log.info(e.getMessage());
        }


//        userInfoMapper.insertUserInfo(userInfoVo);
//          userInfoMapper.insertUserInfo(userInfoVo);
    }

    @Override
    public void update(UserInfoVo userInfoVo) {
        updateUserInfo(userInfoVo);
    }
}
