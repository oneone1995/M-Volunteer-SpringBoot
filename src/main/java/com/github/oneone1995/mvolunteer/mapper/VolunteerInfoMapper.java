package com.github.oneone1995.mvolunteer.mapper;

import com.github.oneone1995.mvolunteer.domain.VolunteerDetails;
import com.github.oneone1995.mvolunteer.domain.VolunteerInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface VolunteerInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VolunteerInfo record);

    VolunteerInfo selectByPrimaryKey(Integer id);

    List<VolunteerInfo> selectAll();

    int updateByPrimaryKey(VolunteerInfo record);


    /**
     * 根据用户id数组和活动工时更新用户工时
     * @param userIds   用户id
     * @param workingHours  工时
     * @return  更新记录数
     */
    int updateWorkingHoursByIdAndWorkingHours(
            @Param("userIds") List<Integer> userIds,
            @Param("workingHours") double workingHours);

    /**
     * 根据组织id查询该组织的志愿者
     * @param organizationId    组织id
     * @return  包含了证书状态的志愿者列表
     */
    List<VolunteerDetails> selectByOrganizationId(
            @Param("organizationId") Integer organizationId);
}