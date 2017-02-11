package com.github.oneone1995.mvolunteer.service.impl;

import com.github.oneone1995.mvolunteer.domain.CustomUserDetails;
import com.github.oneone1995.mvolunteer.domain.User;
import com.github.oneone1995.mvolunteer.mapper.RoleMapper;
import com.github.oneone1995.mvolunteer.mapper.UserMapper;
import com.github.oneone1995.mvolunteer.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by wangl on 2017/2/10.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserById(int id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CustomUserDetails user = userMapper.selectCustomUserDetailsByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find the user '" + username + "'");
        }

        return user;
    }
}
