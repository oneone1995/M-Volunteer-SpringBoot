package com.github.oneone1995.mvolunteer.service;

import com.github.oneone1995.mvolunteer.domain.ActivityUser;
import com.github.oneone1995.mvolunteer.domain.VolunteerDetails;
import com.github.pagehelper.PageInfo;

/**
 * Created by wangl on 2017/2/23.
 * 志愿者活动报名、取消报名，志愿组织面试管理相关业务处理接口
 */
public interface ActivityUserService {
    /**
     * 报名活动
     * @param activityUser 活动用户表对应的实体{@link com.github.oneone1995.mvolunteer.domain.ActivityUser}
     * @return 报名成功与否的标志
     */
    boolean signUpActivity(ActivityUser activityUser);

    boolean cancelActivityByActivityId(Integer activityId);

    PageInfo<VolunteerDetails> getInterviewList(Integer page, Integer rows);

    /**
     * 修改面试状态
     * @param id 活动报名表id
     * @param activityUserStatusId 需要修改的面试状态
     * @return 修改面试状态是否成功的标志
     */
    boolean modifyInterviewStatus(Integer id, Integer activityUserStatusId);
}
