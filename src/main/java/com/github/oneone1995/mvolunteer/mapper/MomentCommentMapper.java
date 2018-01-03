package com.github.oneone1995.mvolunteer.mapper;

import com.github.oneone1995.mvolunteer.domain.moment.MomentComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MomentCommentMapper {
    MomentComment selectByPrimaryKey(Integer id);

    int insert(MomentComment record);

    List<MomentComment> selectByMomentId(@Param("momentId") Integer momentId);
}