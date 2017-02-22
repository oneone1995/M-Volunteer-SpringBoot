package com.github.oneone1995.mvolunteer.service.impl;

import com.github.oneone1995.mvolunteer.domain.ActivityStatus;
import com.github.oneone1995.mvolunteer.mapper.ActivityStatusMapper;
import com.github.oneone1995.mvolunteer.service.ActivityStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangl on 2017/2/22.
 */
@Service
public class ActivityStatusServiceImpl implements ActivityStatusService {
    @Autowired
    private ActivityStatusMapper activityStatusMapper;

    @Override
    public ActivityStatus getActivityStatusById(Integer id) {
        ActivityStatus activityStatus = activityStatusMapper.selectByPrimaryKey(id);

        if (activityStatus == null) {
            return null;
        }
        return activityStatus;
    }
}
