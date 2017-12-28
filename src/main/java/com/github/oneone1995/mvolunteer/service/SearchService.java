package com.github.oneone1995.mvolunteer.service;

import com.github.oneone1995.mvolunteer.domain.HomeActivity;
import com.github.oneone1995.mvolunteer.domain.User;
import com.github.oneone1995.mvolunteer.web.exception.GroupNotFoundException;
import com.github.oneone1995.mvolunteer.web.exception.UserNotFoundException;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by wangl on 2017/2/20.
 */
public interface SearchService {
    //分页返回搜索结果
    PageInfo<HomeActivity> getHomeActivityPageInfo(
            Integer page, Integer rows, double coordLong, double coordLat, String activityName);

    //根据username搜索用户
    User getUserByUsername(String username) throws UserNotFoundException;

    //根据群id返回群头像
    String getGroupAvatar(String groupId) throws GroupNotFoundException;
}
