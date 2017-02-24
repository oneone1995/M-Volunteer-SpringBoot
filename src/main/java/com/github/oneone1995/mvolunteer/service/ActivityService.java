package com.github.oneone1995.mvolunteer.service;

import com.github.oneone1995.mvolunteer.domain.Activity;
import com.github.oneone1995.mvolunteer.domain.ActivityDetails;
import com.github.oneone1995.mvolunteer.domain.HomeActivity;
import com.github.pagehelper.PageInfo;

/**
 * Created by wangl on 2017/2/18.
 */
public interface ActivityService {
    //分页展示首页的活动列表的接口
    PageInfo<HomeActivity> getHomeActivityPageInfo(
            Integer page, Integer rows, double coordLong, double coordLat);

    //搜索活动列表
    //分页返回搜索结果
    PageInfo<HomeActivity> getHomeActivityPageInfo(
            Integer page, Integer rows, double coordLong, double coordLat,
            String activityName);

    //根据活动类别返回对应活动列表
    //分页返回结果
    PageInfo<HomeActivity> getHomeActivityPageInfo(
            Integer page, Integer rows, double coordLong, double coordLat,
            String category, Integer collation, String district);

    //根据活动id和经纬度返回包装有志愿者等信息的活动详情
    ActivityDetails getActivityById(
            Integer id, double coordLong, double coordLat);

    //创建活动
    boolean createActivity(Activity activity);

    //根据志愿者id返回该志愿者参与的活动列表
    PageInfo<ActivityDetails> getActivityPageInfoByVolunteerId(
            Integer page, Integer rows, Integer id);

    //根据志愿者id返回该志愿者的服务历史
    PageInfo<ActivityDetails> getHistoricalActivityPageInfo(
            Integer page, Integer rows, Integer id);

    //根据组织id返回该组织的所有活动
    PageInfo<ActivityDetails> getActivityPageInfoByOrganizationId(
            Integer page, Integer rows, Integer id);

    //根据活动id和状态id更新活动
    String updateActivityStatusById(
            Integer id, Integer activityStatusId);
}
