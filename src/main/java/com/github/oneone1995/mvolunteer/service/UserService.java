package com.github.oneone1995.mvolunteer.service;


import com.github.oneone1995.mvolunteer.domain.User;
import com.github.oneone1995.mvolunteer.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by wangl on 2017/2/5.
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User findUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

}
