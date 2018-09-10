package com.learn.ssm.annotations.service.impl;

import com.learn.ssm.annotations.mapper.UserInfoMapper;
import com.learn.ssm.annotations.service.UserInfoService;
import com.learn.ssm.annotations.service.UserService;
import com.learn.ssm.annotations.vo.UserInfoVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserInfoService userInfoService;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Override
    @Transactional(value = "localTransactionManager")
    public void update(UserInfoVo userInfoVo) {
        userInfoMapper.insertUserInfo(userInfoVo);

    }
}
