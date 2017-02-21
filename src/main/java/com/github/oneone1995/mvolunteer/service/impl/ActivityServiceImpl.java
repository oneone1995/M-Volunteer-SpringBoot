package com.github.oneone1995.mvolunteer.service.impl;

import com.github.oneone1995.mvolunteer.domain.HomeActivity;
import com.github.oneone1995.mvolunteer.mapper.ActivityMapper;
import com.github.oneone1995.mvolunteer.service.ActivityService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangl on 2017/2/18.
 */
@Service
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private ActivityMapper activityMapper;

    @Override
    public PageInfo<List<HomeActivity>> getHomeActivityPageInfo(
            Integer page, Integer rows, double coordLong, double coordLat) {

        PageHelper.startPage(page, rows);
        List<HomeActivity> homeActivityList = activityMapper.selectAllOderByTime(
                coordLong, coordLat);

        if (homeActivityList == null || homeActivityList.isEmpty())
            return null;

        PageInfo homeActivityPageInfo = new PageInfo<>(homeActivityList);
        return homeActivityPageInfo;
    }

    @Override
    public PageInfo<List<HomeActivity>> getHomeActivityPageInfo(
            Integer page, Integer rows, double coordLong, double coordLat,
            String activityName) {

        PageHelper.startPage(page, rows);
        List<HomeActivity> searchActivityList = activityMapper.selectByActivityName(
                coordLong, coordLat, activityName);

        if (searchActivityList == null || searchActivityList.isEmpty())
            return null;

        PageInfo searchActivityPageInfo = new PageInfo<>(searchActivityList);
        return searchActivityPageInfo;
    }

    @Override
    public PageInfo<List<HomeActivity>> getHomeActivityPageInfo(
            Integer page, Integer rows, double coordLong, double coordLat,
            String category, Integer collation) {

        PageHelper.startPage(page, rows);
        List<HomeActivity> categoryActivityList = activityMapper.selectByCategory(
                coordLong, coordLat, category, collation);

        if (categoryActivityList == null || categoryActivityList.isEmpty())
            return null;

        PageInfo categoryActivityPageInfo = new PageInfo<>(categoryActivityList);
        return categoryActivityPageInfo;
    }
}
