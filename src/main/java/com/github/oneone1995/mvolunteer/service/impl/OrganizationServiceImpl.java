package com.github.oneone1995.mvolunteer.service.impl;

import com.github.oneone1995.mvolunteer.domain.OrganizationInfo;
import com.github.oneone1995.mvolunteer.mapper.OrganizationInfoMapper;
import com.github.oneone1995.mvolunteer.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wangl on 2017/2/24.
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    @Autowired
    private OrganizationInfoMapper organizationInfoMapper;

    @Override
    public OrganizationInfo getOrganizationInfoBy(Integer id) {
        OrganizationInfo organizationInfo = organizationInfoMapper.selectByPrimaryKey(id);

        if (organizationInfo == null) {
            return null;
        }
        return organizationInfo;
    }
}
