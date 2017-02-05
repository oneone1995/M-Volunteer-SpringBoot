package com.github.oneone1995.mvolunteer.mapper;

import com.github.oneone1995.mvolunteer.domain.User;
import com.github.oneone1995.mvolunteer.util.MyMapper;

/**
 * Created by wangl on 2017/2/5.
 * UserMapper,映射SQL语句的接口，无具体实现
 */
public interface UserMapper extends MyMapper<User>{
    User findUserById(int id);
}
