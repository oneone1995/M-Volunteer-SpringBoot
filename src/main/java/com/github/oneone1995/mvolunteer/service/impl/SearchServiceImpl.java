package com.github.oneone1995.mvolunteer.service.impl;

import com.github.oneone1995.mvolunteer.config.result.ResultStatus;
import com.github.oneone1995.mvolunteer.domain.HomeActivity;
import com.github.oneone1995.mvolunteer.domain.User;
import com.github.oneone1995.mvolunteer.mapper.ActivityMapper;
import com.github.oneone1995.mvolunteer.mapper.UserMapper;
import com.github.oneone1995.mvolunteer.service.ActivityService;
import com.github.oneone1995.mvolunteer.service.SearchService;
import com.github.oneone1995.mvolunteer.web.exception.GroupNotFoundException;
import com.github.oneone1995.mvolunteer.web.exception.UserNotFoundException;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangl on 2017/2/20.
 */
@Service
@Slf4j
public class SearchServiceImpl implements SearchService {
    @Autowired
    private ActivityService activityService;

    @Resource
    private UserMapper userMapper;

    @Resource
    private ActivityMapper activityMapper;

    @Override
    public PageInfo<HomeActivity> getHomeActivityPageInfo(Integer page, Integer rows, double coordLong, double coordLat, String activityName) {
        return activityService.getHomeActivityPageInfo(page, rows, coordLong, coordLat, activityName);
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException {
        log.info("search user info by username: {}", username);

        User user = userMapper.selectByUsername(username);
        if (user == null) {
            log.error("user not found by username :{}", username);
            throw new UserNotFoundException(ResultStatus.USER_NOT_FOUND.getMessage());
        }
        log.info("search user : {} success", user);
        return user;
    }

    @Override
    public String getGroupAvatar(String groupId) throws GroupNotFoundException {
        log.info("search activity picture use for group avatar by group id:{}", groupId);

        String groupAvatar = activityMapper.selectActivityPictureByTribeId(groupId);

        if (groupAvatar == null || groupAvatar.length() == 0) {
            log.error("group not found by group id:{}", groupId);
            throw new GroupNotFoundException(ResultStatus.GROUP_NOT_FOUND.getMessage());
        }
        log.info("get group avatar success by activity picture:{}", groupAvatar);
        return groupAvatar;
    }
}
