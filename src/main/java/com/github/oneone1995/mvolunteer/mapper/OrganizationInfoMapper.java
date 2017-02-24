package com.github.oneone1995.mvolunteer.mapper;

import com.github.oneone1995.mvolunteer.domain.OrganizationInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrganizationInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrganizationInfo record);

    OrganizationInfo selectByPrimaryKey(Integer id);

    List<OrganizationInfo> selectAll();

    int updateByPrimaryKey(OrganizationInfo record);
}