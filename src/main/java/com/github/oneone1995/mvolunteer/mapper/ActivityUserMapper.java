package com.github.oneone1995.mvolunteer.mapper;

import com.github.oneone1995.mvolunteer.domain.ActivityUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActivityUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityUser record);

    ActivityUser selectByPrimaryKey(Integer id);

    List<ActivityUser> selectAll();

    int updateByPrimaryKey(ActivityUser record);

    /**
     * 根据用户id和活动id查询出关系表中符合条件的记录的id
     * @param activityId    活动id
     * @param userId    用户id
     * @return  活动用户关系表的主键id
     */
    Integer selectPrimaryKeyByUserIdAndActivityId(
            @Param("activityId") Integer activityId,
            @Param("userId") Integer userId
    );
}