package com.github.oneone1995.mvolunteer.service;

import com.github.oneone1995.mvolunteer.domain.moment.Moment;
import com.github.oneone1995.mvolunteer.web.exception.MomentCreateFailException;
import com.github.oneone1995.mvolunteer.web.exception.MomentNotFoundException;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 圈子动态相关接口
 */
public interface MomentService {

    /**
     * 发布圈子动态
     * @param moment 圈子动态实体
     * @return 是否创建成功
     */
    boolean createMoment(Moment moment) throws MomentCreateFailException;

    /**
     * 分页查询圈子动态
     * @param page 页码
     * @param rows 每页的数量
     * @return 圈子动态PageHelper分页对象
     * @throws MomentNotFoundException 当前没有动态
     */
    PageInfo<Moment> getMoment(Integer page, Integer rows) throws MomentNotFoundException;

    Moment getMomentById(Integer id) throws MomentNotFoundException;
}
