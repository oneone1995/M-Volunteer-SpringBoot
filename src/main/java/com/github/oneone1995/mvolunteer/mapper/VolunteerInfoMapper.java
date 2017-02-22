package com.github.oneone1995.mvolunteer.mapper;

import com.github.oneone1995.mvolunteer.domain.VolunteerInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface VolunteerInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VolunteerInfo record);

    VolunteerInfo selectByPrimaryKey(Integer id);

    List<VolunteerInfo> selectAll();

    int updateByPrimaryKey(VolunteerInfo record);
}