package com.learn.ssm.annotations.mapper;

import com.learn.ssm.annotations.vo.UserInfoVo;

public interface UserInfoMapper {

    void updateUserInfo(UserInfoVo userInfoVo);

    void insertUserInfo(UserInfoVo userInfoVo);
}
