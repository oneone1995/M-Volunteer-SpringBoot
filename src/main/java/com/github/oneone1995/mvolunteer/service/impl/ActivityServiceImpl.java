package com.github.oneone1995.mvolunteer.service.impl;

import ch.hsr.geohash.GeoHash;
import com.github.oneone1995.mvolunteer.domain.Activity;
import com.github.oneone1995.mvolunteer.domain.ActivityDetails;
import com.github.oneone1995.mvolunteer.domain.CustomUserDetails;
import com.github.oneone1995.mvolunteer.domain.HomeActivity;
import com.github.oneone1995.mvolunteer.mapper.ActivityMapper;
import com.github.oneone1995.mvolunteer.mapper.ActivityUserMapper;
import com.github.oneone1995.mvolunteer.mapper.VolunteerInfoMapper;
import com.github.oneone1995.mvolunteer.model.EasemobIMChatGroupModel;
import com.github.oneone1995.mvolunteer.model.EasemobIMChatMessage;
import com.github.oneone1995.mvolunteer.service.ActivityService;
import com.github.oneone1995.mvolunteer.service.CacheService;
import com.github.oneone1995.mvolunteer.service.EasemobIMService;
import com.github.oneone1995.mvolunteer.web.exception.EasemobGroupCreateFailException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by wangl on 2017/2/18.
 */
@Service
@Slf4j
public class ActivityServiceImpl implements ActivityService {
    //建立环信群时群名的后缀
    private static final String GROUP_BASE_NAME = "交流群";

    //建立环信群时群成员的额外人数(除了需要招募的志愿者人数之外的人数)
    private static final Integer GROUP_MAX_USER_BASE_NUMBER = 10;

    //geohash字符串长度
    private static final int GEOHASH_CHARACTERS_LENGTH = 12;

    //geohash匹配前缀字符位数
    private static final int GEOHASH_PREFIX_LENGTH = 5;

    //活动状态标志，用于修改活动状态为 正在招募。注意实际上是不可将活动变为该状态
    private static final int RECRUITING = 1;

    //活动状态标志，用于修改活动状态为 活动进行
    private static final int STRAT_ACTIVITY = 2;

    //活动状态标志，用于修改活动状态为 活动结束
    private static final int END_ACTIVITY = 3;

    //环信消息目标类型
    private static final String EASEMOB_MESSAGE_TARGET_TYPE = "chatgroups";

    //环信文本消息类型
    private static final String EASEMOB_TEXT_TYPE_MESSAGE = "txt";

    //创建环信群组成功的系统消息
    private static final String EASEMOB_GROUP_CREATED_SUCCESS_MESSAGE = "成功创建群组";

    @Resource
    private ActivityMapper activityMapper;

    @Resource
    private ActivityUserMapper activityUserMapper;

    @Resource
    private VolunteerInfoMapper volunteerInfoMapper;

    @Autowired
    @Lazy
    private EasemobIMService easemobIMService;

    @Autowired
    private CacheService cacheService;

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

        String geohash = GeoHash.geoHashStringWithCharacterPrecision(coordLat, coordLong, GEOHASH_CHARACTERS_LENGTH);
        log.info("geohash string build by latitude: {}, longitude: {}, geohash result : {}", coordLat, coordLong, geohash);

        //todo 现在只是将移动设备的geohash做简单的MySQL前缀匹配,能找到离我最近的活动，但存在的问题是不能按距离排序以及geohash本身的边界问题
        PageHelper.startPage(page, rows);
        List<HomeActivity> categoryActivityList = activityMapper.selectByCategory(
                coordLong, coordLat, category, collation, district, geohash.substring(0, GEOHASH_PREFIX_LENGTH));

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

        //从redis中取一个code来设置当前新发布活动的code值
        activity.setCode(cacheService.getRandomActivityCode());

        //设置geohash值
        activity.setGeohash(GeoHash.geoHashStringWithCharacterPrecision(
                        activity.getCoordLat(), activity.getCoordLong(), GEOHASH_CHARACTERS_LENGTH));

        //获取当前用户
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        activity.setOrganizationId(currentUser.getId());

        //如果活动创建成功
        //这里是考虑到第三方SDK群创建成功但是活动却插入失败，则会多建一个无用的群，但是如果先插入再建群，建群失败则可以事务回滚
        if (activityMapper.insertActivity(activity) > 0) {
            //创建群组
            // 1. 构造环信群组API交互model, 默认群名为活动名,群描述为活动名称加上"交流群"组成的字符串,群私有,不允许拉人,最大人数为活动招募人数加上10个可能会用到的
            EasemobIMChatGroupModel chatGroupModel = new EasemobIMChatGroupModel(
                    null,
                    activity.getName(),
                    activity.getName() + GROUP_BASE_NAME ,
                    false,
                    false,
                    false,
                    activity.getRecruitPersonNumber() + GROUP_MAX_USER_BASE_NUMBER,
                    currentUser.getUsername());
            //2. 调用环信API
            try {
                String groupId = easemobIMService.createGroup(chatGroupModel);

                // ====异步调用发送消息方法
                easemobIMService.sendEasemobGroupMessage(
                        new EasemobIMChatMessage(
                                EASEMOB_MESSAGE_TARGET_TYPE,
                                new String[]{groupId},
                                new EasemobIMChatMessage.Message(EASEMOB_TEXT_TYPE_MESSAGE, EASEMOB_GROUP_CREATED_SUCCESS_MESSAGE),
                                "admin"));
                // 异步调用结束

                //将群号存下来的目的是为了活动结束或者删除活动时把对应的群删了
                return activityMapper.updateActivityTribeId(groupId, activity.getId()) > 0;
            } catch (EasemobGroupCreateFailException e) {
                throw new RuntimeException(e);
            }
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

        //下面两种情况不允许更改活动状态
        //1. 需要更新状态的活动已经是结束活动状态 END_ACTIVITY
        //2. 企图将活动状态改为招募志愿者状态 RECRUITING
        if (activity.getActivityStatusId() == END_ACTIVITY || activityStatusId == RECRUITING) {
            return "IMMUTABLE";
        }
        //当活动存在且为可更改状态时，更新活动状态
        activity.setActivityStatusId(activityStatusId);
        //若活动状态更新为3时，即更新为活动结束时，还需更新参加此次活动的用户的工时
        //2017.12.21 活动结束后回收活动的code码放回redis复用，并将数据库中原本存的code置零处理,如果将活动由招募状态更改为进行活动则不需要走下面的逻辑
        if (activityStatusId == END_ACTIVITY) {
            //回收code码
            String code = activity.getCode();
            activity.setCode("000000");
            //更新活动表中的活动状态
            activityMapper.updateByPrimaryKey(activity);

            //查询报名此次活动并签到成功的志愿者id列表
            List<Integer> userIds = activityUserMapper.selectAllByActivityId(id);
            if (userIds == null || userIds.isEmpty()) {
                //没有人报名直接返回更新状态成功，但仍然需要将code回收
                cacheService.putRandomActivityCode(code);
                return "SUCCESS";
            }

            if (volunteerInfoMapper.updateWorkingHoursByIdAndWorkingHours
                    (userIds, activity.getWorkingHours()) > 0) {
                //数据库操作均成功后才将code放回redis中
                cacheService.putRandomActivityCode(code);
                return "SUCCESS";
            } else {
                return "FAIL";
            }
        }
        return activityMapper.updateByPrimaryKey(activity) > 0 ? "SUCCESS":"FAIL";
    }
}
