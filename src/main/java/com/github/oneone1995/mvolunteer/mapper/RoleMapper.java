package com.github.oneone1995.mvolunteer.mapper;

import com.github.oneone1995.mvolunteer.domain.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Role record);

    Role selectByPrimaryKey(Integer id);

    List<Role> selectAll();

    int updateByPrimaryKey(Role record);

    List<Role> getUserRolesByUserId(Integer userId);
}