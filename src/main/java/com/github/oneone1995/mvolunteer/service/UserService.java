package com.github.oneone1995.mvolunteer.service;


import com.github.oneone1995.mvolunteer.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by wangl on 2017/2/5.
 */

public interface UserService extends UserDetailsService{
    User getUserById(int id);
}
