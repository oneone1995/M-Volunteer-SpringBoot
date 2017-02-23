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

    //根据活动id返回包装有志愿者信息的活动详情
    ActivityDetails getActivityById(Integer id);

    //创建活动
    boolean createActivity(Activity activity);

    //根据志愿者id返回该志愿者参与的活动列表
    PageInfo<ActivityDetails> getActivityPageInfoByVolunteerId(
            Integer page, Integer rows, Integer id);

    //根据志愿者id返回该志愿者的服务历史
    PageInfo<ActivityDetails> getHistoricalActivityPageInfo(
            Integer page, Integer rows, Integer id);
}
