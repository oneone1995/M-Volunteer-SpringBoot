package com.github.oneone1995.mvolunteer.mapper;

import com.github.oneone1995.mvolunteer.domain.HomeActivity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wangl on 2017/2/19.
 */
@Mapper
public interface ActivityMapper {

    List<HomeActivity> selectAllOderByTime(
            @Param("coordLong") double coordLong, @Param("coordLat") double coordLat);
}
