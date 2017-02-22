package com.github.oneone1995.mvolunteer.mapper;

import com.github.oneone1995.mvolunteer.domain.Activity;
import com.github.oneone1995.mvolunteer.domain.ActivityDetails;
import com.github.oneone1995.mvolunteer.domain.HomeActivity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * Created by wangl on 2017/2/19.
 */
@Mapper
public interface ActivityMapper {
    /**
     * 根据活动添加时间排序查询活动列表返回
     * 并根据参数中的经纬度求出距离并将其包装进HomeActivity中的mapper接口
     * @param coordLong 经度
     * @param coordLat  维度
     * @return  首页展示的活动列表
     */
    List<HomeActivity> selectAllOderByTime(
            @Param("coordLong") double coordLong, @Param("coordLat") double coordLat);

    List<HomeActivity> selectByActivityName(
            @Param("coordLong") double coordLong, @Param("coordLat") double coordLat,
            @Param("activityName") String activityName
    );

    List<HomeActivity> selectByCategory(
            @Param("coordLong") double coordLong, @Param("coordLat") double coordLat,
            @Param("category") String category, @Param("collation") Integer collation,
            @Param("district") String district
    );

    /**
     * 根据活动id返回活动详情的mapper接口
     * @param id    活动id
     * @return  活动详情
     */
    ActivityDetails selectByPrimaryKey(
            @Param("id") Integer id);

    /**
     *
     * @param code  活动代码
     * @param id    用户id
     * @return  根据活动code和用户id返回该记录在活动用户关系表中的主键id
     */
    Integer selectByCode(
            @Param("code") Integer code, @Param("id") Integer id);

    /**
     * 根据活动用户关系表主键更新签到状态
     * @param id    用户id
     * @return  更新记录
     */
    Integer updateSignStatusByPrimaryKey(
            @Param("id") Integer id
    );

    /**
     * 根据活动实体插入活动
     * @param activity  活动实体
     * @return  更新记录数
     */
    Integer insertActivity(Activity activity);

    /**
     * 查询所有活动代码
     * @return 所有活动代码的集合
     */
    Set<Integer> selectAllCode();

    /**
     * 根据志愿者id查询其参加的未结束的活动
     * @param id    志愿者id
     * @return  当前登录用户其参加的未结束的活动列表
     */
    List<ActivityDetails> selectByVolunteerId(@Param("id") Integer id);
}
