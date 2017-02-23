package com.github.oneone1995.mvolunteer.mapper;

import com.github.oneone1995.mvolunteer.domain.ActivityUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityUser record);

    ActivityUser selectByPrimaryKey(Integer id);

    List<ActivityUser> selectAll();

    int updateByPrimaryKey(ActivityUser record);
}