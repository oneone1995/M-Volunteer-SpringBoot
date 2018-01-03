package com.github.oneone1995.mvolunteer.service;

import com.github.oneone1995.mvolunteer.domain.moment.Moment;
import com.github.oneone1995.mvolunteer.domain.moment.MomentComment;
import com.github.oneone1995.mvolunteer.web.exception.MomentCommentCreateFailException;
import com.github.oneone1995.mvolunteer.web.exception.MomentCommentNotFoundException;
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

    /**
     * 根据id查看某条具体的动态
     * @param id 动态id
     * @return 动态详情
     * @throws MomentNotFoundException 动态未找到异常
     */
    Moment getMomentById(Integer id) throws MomentNotFoundException;

    /**
     * 根据id查看某条评论
     * @param id 评论id
     * @return 评论详情
     * @throws MomentCommentNotFoundException 评论未找到异常
     */
    MomentComment getMomentCommentById(Integer id) throws MomentCommentNotFoundException;

    /**
     * 创建动态评论
     * @param comment 评论实体
     * @param momentId 动态id
     * @return 创建评论是否成功的标志
     * @throws MomentCommentCreateFailException 评论创建失败的异常
     */
    boolean createMomentComment(Integer momentId, MomentComment comment) throws MomentCommentCreateFailException;

    /**
     * 根据动态id查看这条动态的评论
     * @param momentId 动态id
     * @return 评论列表
     * @throws MomentCommentNotFoundException 评论找不到异常
     */
    List<MomentComment> getMomentCommentsByMomentId(Integer momentId) throws MomentCommentNotFoundException;
}
