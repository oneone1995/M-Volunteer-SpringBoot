package com.github.oneone1995.mvolunteer.service;

import com.github.oneone1995.mvolunteer.domain.VolunteerDetails;
import com.github.oneone1995.mvolunteer.domain.VolunteerInfo;
import com.github.pagehelper.PageInfo;

/**
 * Created by wangl on 2017/2/22.
 */
public interface VolunteerService {
    VolunteerInfo getVolunteerById(Integer id);

    PageInfo<VolunteerDetails> getVolunteerListPageInfoByOrganizationId(
            Integer page, Integer rows, Integer id);
}
