package com.learn.ssm.annotations.service;

import com.learn.ssm.annotations.vo.UserInfoVo;

public interface UserInfoService {

  void updateUserInfo(UserInfoVo userInfoVo);

  void update(UserInfoVo userInfoVo);
}
