package com.github.oneone1995.mvolunteer.domain.moment;

import com.github.oneone1995.mvolunteer.domain.User;
import lombok.Data;

import java.util.Date;

/**
 * 圈子动态实体，翻译参考自微信
 */
@Data
public class Moment {
    private Integer id;

    //动态发布者id
    private Integer userId;

    //动态发布者详情,包括发布者姓名、头像
    private User author;

    //动态文字内容
    private String content;

    //动态图片地址,这里可能是多张图，用英文分号隔开
    private String images;

    //动态发布时间
    private Date time;
}
