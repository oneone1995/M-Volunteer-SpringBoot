package com.github.oneone1995.mvolunteer.service;

/**
 * Created by wangl on 2017/2/21.
 *签到的业务代码接口
 */
public interface SignInService {
    //根据活动签到码进行签到
    String signIn(String code);
}
