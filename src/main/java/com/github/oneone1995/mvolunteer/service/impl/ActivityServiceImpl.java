package com.github.oneone1995.mvolunteer.service.impl;

import com.github.oneone1995.mvolunteer.domain.Activity;
import com.github.oneone1995.mvolunteer.domain.ActivityDetails;
import com.github.oneone1995.mvolunteer.domain.HomeActivity;
import com.github.oneone1995.mvolunteer.mapper.ActivityMapper;
import com.github.oneone1995.mvolunteer.service.ActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by wangl on 2017/2/18.
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private ActivityMapper activityMapper;

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
    public ActivityDetails getActivityById(Integer id) {
        ActivityDetails activityDetails = activityMapper.selectByPrimaryKey(id);

        if (activityDetails == null) {
            return null;
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

        return activityMapper.insertActivity(activity) > 0;
    }
}
