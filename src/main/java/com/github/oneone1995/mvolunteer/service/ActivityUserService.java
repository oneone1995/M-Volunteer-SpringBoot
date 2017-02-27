package com.github.oneone1995.mvolunteer.service;

import com.github.oneone1995.mvolunteer.domain.ActivityUser;
import com.github.oneone1995.mvolunteer.domain.VolunteerDetails;
import com.github.pagehelper.PageInfo;

/**
 * Created by wangl on 2017/2/23.
 */
public interface ActivityUserService {
    boolean signUpActivity(ActivityUser activityUser);

    boolean cancelActivityByActivityId(Integer activityId);

    PageInfo<VolunteerDetails> getInterviewList(Integer page, Integer rows);

    boolean modifyInterviewStatus(Integer id, Integer activityUserStatusId);
}
