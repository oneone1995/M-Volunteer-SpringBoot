package com.github.oneone1995.mvolunteer.service.impl;

import com.github.oneone1995.mvolunteer.domain.VolunteerDetails;
import com.github.oneone1995.mvolunteer.domain.VolunteerInfo;
import com.github.oneone1995.mvolunteer.mapper.VolunteerInfoMapper;
import com.github.oneone1995.mvolunteer.service.VolunteerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public PageInfo<VolunteerDetails> getVolunteerListPageInfoByOrganizationId(
            Integer page, Integer rows, Integer id) {

        PageHelper.startPage(page, rows);
        List<VolunteerDetails> volunteerDetailsList = volunteerInfoMapper.selectByOrganizationId(id);

        if (volunteerDetailsList == null || volunteerDetailsList.isEmpty()) {
            return null;
        }
        return new PageInfo<>(volunteerDetailsList);
    }
}
