package com.github.oneone1995.mvolunteer.service.impl;

import com.github.oneone1995.mvolunteer.domain.HomeActivity;
import com.github.oneone1995.mvolunteer.service.ActivityService;
import com.github.oneone1995.mvolunteer.service.SearchService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangl on 2017/2/20.
 */
@Service
public class SearchServiceImpl implements SearchService {
    @Autowired
    private ActivityService activityService;

    @Override
    public PageInfo<HomeActivity> getHomeActivityPageInfo(Integer page, Integer rows, double coordLong, double coordLat, String activityName) {
        return activityService.getHomeActivityPageInfo(page, rows, coordLong, coordLat, activityName);
    }
}
