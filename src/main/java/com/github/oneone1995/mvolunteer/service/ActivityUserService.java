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

    /**
     * 分页返回待面试的志愿者列表
     * 其中志愿者信息包括了志愿者的面试状态id、活动报名表中的对应的记录id、所报名的活动id、活动名、活动群组id、志愿者姓名、志愿者头像
     * @see com.github.oneone1995.mvolunteer.domain.VolunteerDetails
     * @param page 页码
     * @param rows 每页的数量
     * @return 待面试的志愿者列表经PageInfo包装的结果
     */
    PageInfo<VolunteerDetails> getInterviewList(Integer page, Integer rows);

    /**
     * 修改面试状态
     * @param id 活动报名表id
     * @param volunteerName 修改的志愿者username
     * @param activityGroupId 活动群组id
     * @param activityUserStatusId 需要修改的面试状态
     * @return 修改面试状态是否成功的标志
     */
    boolean modifyInterviewStatus(Integer id, String volunteerName, String activityGroupId, Integer activityUserStatusId);
}
