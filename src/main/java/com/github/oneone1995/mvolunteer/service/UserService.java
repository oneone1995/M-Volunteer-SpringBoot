package com.github.oneone1995.mvolunteer.service;


import com.github.oneone1995.mvolunteer.domain.User;
import com.github.oneone1995.mvolunteer.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by wangl on 2017/2/5.
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public User findUserById(int id) {
        return userMapper.findUserById(id);
    }

}
