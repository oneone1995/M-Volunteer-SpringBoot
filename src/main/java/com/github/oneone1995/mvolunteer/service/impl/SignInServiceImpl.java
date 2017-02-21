package com.github.oneone1995.mvolunteer.service.impl;

import com.github.oneone1995.mvolunteer.domain.CustomUserDetails;
import com.github.oneone1995.mvolunteer.mapper.ActivityMapper;
import com.github.oneone1995.mvolunteer.service.SignInService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by wangl on 2017/2/21.
 */
@Service
public class SignInServiceImpl implements SignInService {
    @Resource
    private ActivityMapper activityMapper;

    @Override
    public String signIn(Integer code) {
        //得到当前登录的用户
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //得到当前用户的id
        Integer id = currentUser.getId();

        //根据活动code和用户id返回该记录在活动用户关系表中的主键id
        Integer activityUserId = activityMapper.selectByCode(code, id);

        if (activityUserId == null) {
            return "fail";
        }
        Integer record = activityMapper.updateSignStatusByPrimaryKey(activityUserId);
        if (record == 1) {
            return "success";
        }
        return "fail";
    }
}
