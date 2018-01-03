package com.github.oneone1995.mvolunteer.domain.moment;

import com.github.oneone1995.mvolunteer.domain.User;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class MomentComment {
    private Integer id;

    //评论的动态id
    private Integer momentId;

    //评论发布者id
    private Integer userId;

    //评论发布者，包括发布者姓名、头像
    private User user;

    //评论内容
    private String content;

    //评论时间
    private Date time;
}