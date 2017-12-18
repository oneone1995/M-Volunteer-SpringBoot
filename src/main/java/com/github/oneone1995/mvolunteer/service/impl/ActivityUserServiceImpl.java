package com.github.oneone1995.mvolunteer.service.impl;

import com.github.oneone1995.mvolunteer.domain.ActivityUser;
import com.github.oneone1995.mvolunteer.domain.CustomUserDetails;
import com.github.oneone1995.mvolunteer.domain.VolunteerDetails;
import com.github.oneone1995.mvolunteer.mapper.ActivityMapper;
import com.github.oneone1995.mvolunteer.mapper.ActivityUserMapper;
import com.github.oneone1995.mvolunteer.service.ActivityUserService;
import com.github.oneone1995.mvolunteer.service.EasemobIMService;
import com.github.oneone1995.mvolunteer.web.exception.PutUserToEasemobGroupFailException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangl on 2017/2/23.
 */
@Service
@Slf4j
public class ActivityUserServiceImpl implements ActivityUserService {
    //面试通过
    private static final Integer INTERVIEW_PASSED = 2;

    @Resource
    private ActivityUserMapper activityUserMapper;

    @Autowired
    private EasemobIMService easemobIMService;

    @Override
    @Transactional
    public boolean signUpActivity(ActivityUser activityUser) {
        //获取当前用户
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //获取当前用户的id
        activityUser.setUserId(currentUser.getId());

        //这里因为接口传入没有activityUserId，因此我们无法知道用户是否已经报名，所以都先去查一次。
        //todo 应该在数据库中这里将user_id和activity_id设为唯一索引来约束。便不需要先查一次
        //获取当前用户id和活动id在关系表中的记录id
        Integer activityUserId = activityUserMapper.selectPrimaryKeyByUserIdAndActivityId(
                activityUser.getActivityId(), activityUser.getUserId());
        ////若已经报名，则不能再次报名，返回报名失败
        if (activityUserId != null) {
            log.error("current user has already sign up activity, username: {}, activityId: {}", currentUser.getUsername(), activityUser.getActivityId());
            return false;
        } else {
            //没有报名则
            //设置面试状态为待面试
            activityUser.setActivityUserStatusId(1);
            //设置评分为0
            activityUser.setStar(0.0);
            //设置签到状态为未签到
            activityUser.setSignInStatus(0);

            log.info("sign up activity, activityUser: {}", activityUser);
            //加入群组逻辑改到面试通过后才加群
            return activityUserMapper.insert(activityUser) > 0;
        }
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

        ////根据记录id删除活动
        return activityUserMapper.deleteByPrimaryKey(id) > 0;

    }

    @Override
    public PageInfo<VolunteerDetails> getInterviewList(Integer page, Integer rows) {
        //获取当前用户
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //获取当前用户的id
        Integer orgId = currentUser.getId();

        PageHelper.startPage(page, rows);
        List<VolunteerDetails> interviewList = activityUserMapper.selectInterviewList(orgId);

        if (interviewList == null || interviewList.isEmpty()) {
            return null;
        }
        return new PageInfo<>(interviewList);
    }

    @Override
    @Transactional
    public boolean modifyInterviewStatus(Integer id, String volunteerName, String activityGroupId, Integer activityUserStatusId) {
        //这里是考虑到第三方SDK加群成功但是修改面试状态失败，用户也会加群。
        //但是如果先修改再建调用api加群，不会有这个问题，api调用失败则可以事务回滚。同样的策略在创建活动时建群也能发现。
        //如果数据库操作成功且是将面试状态修改为面试通过，则调用api将用户加入IM群
        if (activityUserMapper.updateByPrimaryKey(activityUserStatusId, id) > 0 && activityUserStatusId.equals(INTERVIEW_PASSED)) {
            //调用环信api进入活动对应的群组
            try {
                return easemobIMService.putUser2Group(activityGroupId, volunteerName);
            } catch (PutUserToEasemobGroupFailException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
