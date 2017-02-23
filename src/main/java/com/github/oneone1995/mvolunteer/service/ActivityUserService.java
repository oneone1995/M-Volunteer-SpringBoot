package com.github.oneone1995.mvolunteer.service;

import com.github.oneone1995.mvolunteer.domain.ActivityUser;

/**
 * Created by wangl on 2017/2/23.
 */
public interface ActivityUserService {
    boolean signUpActivity(ActivityUser activityUser);

    boolean cancelActivityByActivityId(Integer activityId);
}
