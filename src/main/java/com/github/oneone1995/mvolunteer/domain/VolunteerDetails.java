package com.github.oneone1995.mvolunteer.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by wangl on 2017/2/25.
 * Volunteer包装类，继承自VolunteerInfo，包装了志愿者的证书状态id
 * 用于志愿组织 面试管理 和 证书审核管理 中志愿者列表显示
 */
@Getter
@Setter
public class VolunteerDetails extends VolunteerInfo{
    //下面的字段用于志愿组织面试管理时使用
    //1. 该字段用于志愿组织查询该组织志愿者时显示志愿者的证书状态id
    private Integer certificateStatusId;

    //下面的字段用于志愿组织面试管理时使用
    //1. 该字段用于志愿组织面试管理时显示志愿者的面试状态id
    private Integer interviewStatus;

    //2. 该字段用于志愿组织面试管理时显示志愿者报名的活动名称
    private String activityName;

    //3. 志愿者报名活动对应的活动报名表id，用于后续交互修改面试状态
    private Integer activityUserId;

    //4. 志愿者报名的活动id，用于后续交互进入活动交流群组
    private Integer activityId;

    //5. 志愿者报名的活动群组id，用于后续交互进入活动交流群组
    private String groupId;
}
