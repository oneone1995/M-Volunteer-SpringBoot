package com.github.oneone1995.mvolunteer.mapper;

import com.github.oneone1995.mvolunteer.domain.moment.Moment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MomentMapper {

    /**
     * 根据圈子动态实体插入圈子动态
     * @param moment 圈子动态实体
     * @return 更新记录数
     */
    int insert(Moment moment);

    /**
     * 查询圈子动态列表，其中关联查询出圈子动态发布者的相关信息
     * @return 圈子动态列表
     */
    List<Moment> selectAll();

    Moment selectByPrimaryKey(Integer id);
}