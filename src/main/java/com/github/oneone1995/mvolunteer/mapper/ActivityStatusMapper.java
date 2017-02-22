package com.github.oneone1995.mvolunteer.mapper;

import com.github.oneone1995.mvolunteer.domain.ActivityStatus;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityStatusMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityStatus record);

    ActivityStatus selectByPrimaryKey(Integer id);

    List<ActivityStatus> selectAll();

    int updateByPrimaryKey(ActivityStatus record);
}