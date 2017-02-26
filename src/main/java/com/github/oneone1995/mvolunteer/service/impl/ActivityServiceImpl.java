package com.github.oneone1995.mvolunteer.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.oneone1995.mvolunteer.domain.Activity;
import com.github.oneone1995.mvolunteer.domain.ActivityDetails;
import com.github.oneone1995.mvolunteer.domain.CustomUserDetails;
import com.github.oneone1995.mvolunteer.domain.HomeActivity;
import com.github.oneone1995.mvolunteer.mapper.ActivityMapper;
import com.github.oneone1995.mvolunteer.mapper.ActivityUserMapper;
import com.github.oneone1995.mvolunteer.mapper.VolunteerInfoMapper;
import com.github.oneone1995.mvolunteer.model.IMTribeModel;
import com.github.oneone1995.mvolunteer.service.ActivityService;
import com.github.oneone1995.mvolunteer.utils.IMUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taobao.api.response.OpenimTribeCreateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by wangl on 2017/2/18.
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    protected static Logger logger = LoggerFactory.getLogger(ActivityServiceImpl.class);

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private ActivityUserMapper activityUserMapper;

    @Resource
    private VolunteerInfoMapper volunteerInfoMapper;

    @Override
    public PageInfo<HomeActivity> getHomeActivityPageInfo(
            Integer page, Integer rows, double coordLong, double coordLat) {

        PageHelper.startPage(page, rows);
        List<HomeActivity> homeActivityList = activityMapper.selectAllOderByTime(
                coordLong, coordLat);

        if (homeActivityList == null || homeActivityList.isEmpty())
            return null;

        return new PageInfo<>(homeActivityList);
    }

    @Override
    public PageInfo<HomeActivity> getHomeActivityPageInfo(
            Integer page, Integer rows, double coordLong, double coordLat,
            String activityName) {

        PageHelper.startPage(page, rows);
        List<HomeActivity> searchActivityList = activityMapper.selectByActivityName(
                coordLong, coordLat, activityName);

        if (searchActivityList == null || searchActivityList.isEmpty())
            return null;

        return new PageInfo<>(searchActivityList);
    }

    @Override
    public PageInfo<HomeActivity> getHomeActivityPageInfo(
            Integer page, Integer rows, double coordLong, double coordLat,
            String category, Integer collation, String district) {

        PageHelper.startPage(page, rows);
        List<HomeActivity> categoryActivityList = activityMapper.selectByCategory(
                coordLong, coordLat, category, collation, district);

        if (categoryActivityList == null || categoryActivityList.isEmpty())
            return null;

        return new PageInfo<>(categoryActivityList);
    }

    @Override
    public ActivityDetails getActivityById(
            Integer id, double coordLong, double coordLat) {
        ActivityDetails activityDetails = activityMapper.selectByPrimaryKey(
                id, coordLong, coordLat);

        if (activityDetails == null) {
            return null;
        }

        //报名志愿者的人数
        activityDetails.setRecruitedPersonNumber(activityDetails.getVolunteers().size());

        //查询当前用户是否报名了活动
        //获取当前用户
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //获取当前用户的id
        Integer userId = currentUser.getId();

        //获取当前用户id和活动id在关系表中的记录id
        Integer activityUserId = activityUserMapper.selectPrimaryKeyByUserIdAndActivityId(
                id, userId);
        //若已经报名，则返回true
        if (activityUserId != null) {
            activityDetails.setSignUp(true);
        }

        return activityDetails;
    }

    @Override
    @Transactional
    public boolean createActivity(Activity activity) {
        Set<Integer> codes = activityMapper.selectAllCode();
        //记录原来集合的大小
        int size = codes.size();

        //生成随机数直到set变大
        while (codes.size() == size) {
            activity.setCode(new Random().nextInt(999999));
            codes.add(activity.getCode());
        }

        //获取当前用户
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        activity.setOrganizationId(currentUser.getId());

        //如果活动创建成功
        if (activityMapper.insertActivity(activity) > 0) {
            //创建群组
            OpenimTribeCreateResponse tribeCreateResponse = IMUtil.creteTribe(currentUser, activity.getName(), "欢迎加入" + activity.getName() + "志愿活动");
            //获取response实体
            logger.debug(tribeCreateResponse.getBody());
            IMTribeModel tribeModel = null;
            try {
                tribeModel =  new ObjectMapper().readValue(tribeCreateResponse.getBody(), IMTribeModel.class);
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
            //解析成功后获取群号,存入数据库
            Integer tribeId = Integer.parseInt(String.valueOf(tribeModel.getOpenim_tribe_create_response().getTribe_info().getTribe_id()));
            return activityMapper.updateActivityTribeId(tribeId, activity.getId()) > 0;
        }

        return false;
    }

    @Override
    public PageInfo<ActivityDetails> getActivityPageInfoByVolunteerId(
            Integer page, Integer rows, Integer id) {

        PageHelper.startPage(page, rows);
        List<ActivityDetails> activityDetailsList = activityMapper.selectByVolunteerId(id);

        if (activityDetailsList == null || activityDetailsList.isEmpty()) {
            return null;
        }
        return new PageInfo<>(activityDetailsList);
    }

    @Override
    public PageInfo<ActivityDetails> getHistoricalActivityPageInfo(
            Integer page, Integer rows, Integer id) {

        PageHelper.startPage(page, rows);
        List<ActivityDetails> historicalActivities = activityMapper.selectHistoryByVolunteerId(id);

        if (historicalActivities == null || historicalActivities.isEmpty()) {
            return null;
        }
        return new PageInfo<>(historicalActivities);
    }

    @Override
    public PageInfo<ActivityDetails> getActivityPageInfoByOrganizationId(
            Integer page, Integer rows, Integer id) {

        PageHelper.startPage(page, rows);
        List<ActivityDetails> activityDetailsList = activityMapper.selectByOrganizationId(id);

        if (activityDetailsList == null || activityDetailsList.isEmpty()) {
            return null;
        }
        return new PageInfo<>(activityDetailsList);
    }

    @Override
    @Transactional
    public String updateActivityStatusById(Integer id, Integer activityStatusId) {
        //根据活动id查出活动
        Activity activity = activityMapper.selectAllInfoByPrimaryKey(id);

        if (activity == null) {
            return "ACTIVITY_NOT_FOUNT";
        }

        if (activity.getActivityStatusId() == 3 || activityStatusId == 1) {
            return "IMMUTABLE";
        }
        //当活动存在且为可更改状态时，更新活动状态
        activity.setActivityStatusId(activityStatusId);
        //若活动状态更新为3时，即更新为活动结束时，还需更新参加此次活动的用户的工时
        if (activityStatusId == 3) {
            //更新活动表中的活动状态
            activityMapper.updateByPrimaryKey(activity);

            //查询报名此次活动并签到成功的志愿者id列表
            List<Integer> userIds = activityUserMapper.selectAllByActivityId(id);
            if (userIds == null || userIds.isEmpty()) {
                return "SUCCESS";
            }
            return volunteerInfoMapper.updateWorkingHoursByIdAndWorkingHours(
                    userIds, activity.getWorkingHours()) > 0 ? "SUCCESS":"FAIL";

        }
        return activityMapper.updateByPrimaryKey(activity) > 0 ? "SUCCESS":"FAIL";
    }
}
