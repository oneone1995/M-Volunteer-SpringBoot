package com.github.oneone1995.mvolunteer.service.impl;

import com.github.oneone1995.mvolunteer.config.result.ResultStatus;
import com.github.oneone1995.mvolunteer.domain.CustomUserDetails;
import com.github.oneone1995.mvolunteer.domain.User;
import com.github.oneone1995.mvolunteer.domain.moment.Moment;
import com.github.oneone1995.mvolunteer.domain.moment.MomentComment;
import com.github.oneone1995.mvolunteer.mapper.MomentCommentMapper;
import com.github.oneone1995.mvolunteer.mapper.MomentMapper;
import com.github.oneone1995.mvolunteer.service.MomentService;
import com.github.oneone1995.mvolunteer.web.exception.MomentCommentCreateFailException;
import com.github.oneone1995.mvolunteer.web.exception.MomentCommentNotFoundException;
import com.github.oneone1995.mvolunteer.web.exception.MomentCreateFailException;
import com.github.oneone1995.mvolunteer.web.exception.MomentNotFoundException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class MomentServiceImpl implements MomentService {

    @Resource
    private MomentMapper momentMapper;

    @Resource
    private MomentCommentMapper momentCommentMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean createMoment(Moment moment) throws MomentCreateFailException {
        //获取当前用户
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();

        //这里只需要存发布动态作者的userID即可，因为圈子动态中的用户信息是由数据库通过user_id关联user表维护的
        moment.setUserId(currentUser.getId());
        log.info("create moment service, moment: {}", moment);

        //插入圈子动态记录失败
        if (momentMapper.insert(moment) <= 0) {
            throw new MomentCreateFailException(ResultStatus.MOMENT_CREATE_FAIL.getMessage());
        }

        return true;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public PageInfo<Moment> getMoment(Integer page, Integer rows) throws MomentNotFoundException {

        //在mapper查询语句前使用下面这句代码可以有分页效果
        PageHelper.startPage(page, rows);
        List<Moment> momentList = momentMapper.selectAll();

        if (momentList == null || momentList.isEmpty()) {
            throw new MomentNotFoundException(ResultStatus.MOMENT_NOT_FOUNT.getMessage());
        }

        return new PageInfo<>(momentList);
    }

    @Override
    public Moment getMomentById(Integer id) throws MomentNotFoundException {
        Moment moment = momentMapper.selectByPrimaryKey(id);
        if (moment == null) {
            throw new MomentNotFoundException(ResultStatus.MOMENT_NOT_FOUNT.getMessage());
        }
        log.info("get moment by id, return : {}", moment);
        return moment;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public MomentComment getMomentCommentById(Integer id) throws MomentCommentNotFoundException {
        MomentComment momentComment = momentCommentMapper.selectByPrimaryKey(id);
        if (momentComment == null) {
            throw new MomentCommentNotFoundException(ResultStatus.MOMENT_COMMENT_NOT_FOUNT.getMessage());
        }
        log.info("get moment comment by id, return : {}", momentComment);
        return momentComment;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean createMomentComment(Integer momentId, MomentComment comment) throws MomentCommentCreateFailException {
        //获取当前用户
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();

        comment.setMomentId(momentId);

        comment.setUserId(currentUser.getId());

        log.info("create moment comment, comment :{}", comment);

        if (momentCommentMapper.insert(comment) < 1) {
            log.error("create moment comment fail by comment : {}", comment);
            throw new MomentCommentCreateFailException(ResultStatus.MOMENT_COMMENT_CREATE_FAIL.getMessage());
        }
        return true;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<MomentComment> getMomentCommentsByMomentId(Integer momentId) throws MomentCommentNotFoundException {
        List<MomentComment> momentComments = momentCommentMapper.selectByMomentId(momentId);

        if (momentComments == null || momentComments.isEmpty()) {
            log.error("get moment comments by moment id {} fail", momentId);
            throw new MomentCommentNotFoundException(ResultStatus.MOMENT_COMMENT_NOT_FOUNT.getMessage());
        }
        log.info("get moment comments success, return : {}", momentComments);
        return momentComments;
    }
}
