package com.github.oneone1995.mvolunteer.service;

import com.github.oneone1995.mvolunteer.domain.HomeActivity;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by wangl on 2017/2/20.
 */
public interface SearchService {
    //分页返回搜索结果
    PageInfo<List<HomeActivity>> getHomeActivityPageInfo(
            Integer page, Integer rows, double coordLong, double coordLat, String activityName);
}
