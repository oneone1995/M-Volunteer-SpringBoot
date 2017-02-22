package com.github.oneone1995.mvolunteer.service.impl;

import com.github.oneone1995.mvolunteer.domain.VolunteerInfo;
import com.github.oneone1995.mvolunteer.mapper.VolunteerInfoMapper;
import com.github.oneone1995.mvolunteer.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangl on 2017/2/22.
 */
@Service
public class VolunteerServiceImpl implements VolunteerService {
    @Autowired
    private VolunteerInfoMapper volunteerInfoMapper;

    @Override
    public VolunteerInfo getVolunteerById(Integer id) {
        VolunteerInfo volunteerInfo = volunteerInfoMapper.selectByPrimaryKey(id);

        if (volunteerInfo == null) {
            return null;
        }
        return volunteerInfo;
    }
}
