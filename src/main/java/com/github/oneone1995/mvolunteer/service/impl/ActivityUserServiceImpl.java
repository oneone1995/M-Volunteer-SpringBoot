package com.github.oneone1995.mvolunteer.service.impl;

import com.github.oneone1995.mvolunteer.domain.ActivityUser;
import com.github.oneone1995.mvolunteer.domain.CustomUserDetails;
import com.github.oneone1995.mvolunteer.domain.VolunteerDetails;
import com.github.oneone1995.mvolunteer.mapper.ActivityMapper;
import com.github.oneone1995.mvolunteer.mapper.ActivityUserMapper;
import com.github.oneone1995.mvolunteer.service.ActivityUserService;
import com.github.oneone1995.mvolunteer.utils.IMUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taobao.api.response.OpenimTribeJoinResponse;
import com.taobao.api.response.OpenimTribeQuitResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wangl on 2017/2/23.
 */
@Service
public class ActivityUserServiceImpl implements ActivityUserService {
    protected static Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);

    @Autowired
    private ActivityUserMapper activityUserMapper;

    @Autowired
    private ActivityMapper activityMapper;

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

            //如果报名成功，则加入对应的群组
            if (activityUserMapper.insert(activityUser) > 0) {
                //调用api加入群组
                OpenimTribeJoinResponse tribeJoinResponse = IMUtil.joinTribe(currentUser, activityMapper.selectTribeId(activityUser.getActivityId()));
                //获取response实体
                logger.debug(tribeJoinResponse.getBody());
                return true;
            }
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

        ////根据记录id删除活动,如果退出成功,则退出相应群组
        if (activityUserMapper.deleteByPrimaryKey(id) > 0) {
            //调用api退出群组
            OpenimTribeQuitResponse tribeQuitResponse = IMUtil.tribeQuitResponse(currentUser, activityMapper.selectTribeId(activityId));
            //获取response实体
            logger.debug(tribeQuitResponse.getBody());
            return true;
        }

        return false;
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
    public boolean modifyInterviewStatus(Integer id, Integer activityUserStatusId) {
        return activityUserMapper.updateByPrimaryKey(activityUserStatusId, id) > 0;
    }
}
