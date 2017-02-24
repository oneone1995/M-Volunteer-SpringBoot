package com.github.oneone1995.mvolunteer.service.impl;

import com.github.oneone1995.mvolunteer.domain.ActivityUser;
import com.github.oneone1995.mvolunteer.domain.CustomUserDetails;
import com.github.oneone1995.mvolunteer.mapper.ActivityUserMapper;
import com.github.oneone1995.mvolunteer.service.ActivityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangl on 2017/2/23.
 */
@Service
public class ActivityUserServiceImpl implements ActivityUserService {
    @Autowired
    private ActivityUserMapper activityUserMapper;

    @Override
    @Transactional
    public boolean signUpActivity(ActivityUser activityUser) {
        //获取当前用户
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //获取当前用户的id
        activityUser.setUserId(currentUser.getId());

        //获取当前用户id和活动id在关系表中的记录id
        Integer activityUserId = activityUserMapper.selectPrimaryKeyByUserIdAndActivityId(
                activityUser.getActivityId(), activityUser.getUserId());
        ////若已经报名，则不能再次报名，返回报名失败
        if (activityUserId == null) {
            //设置面试状态为待面试
            activityUser.setActivityUserStatusId(1);
            //设置评分为0
            activityUser.setStar(0.0);
            //设置签到状态为未签到
            activityUser.setSignInStatus(0);

            return activityUserMapper.insert(activityUser) > 0;
        }
        return false;
    }

    @Override
    @Transactional
    public boolean cancelActivityByActivityId(Integer activityId) {
        //获取当前用户
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //获取当前用户的id
        Integer userId = currentUser.getId();

        //获取当前用户id和活动id在关系表中的记录id
        Integer id = activityUserMapper.selectPrimaryKeyByUserIdAndActivityId(
                activityId, userId
        );

        //根据记录id删除活动
        return activityUserMapper.deleteByPrimaryKey(id) > 0;
    }
}
