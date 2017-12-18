package com.github.oneone1995.mvolunteer.domain;

/**
 * 活动用户表对应的实体域对象。维护了志愿者报名活动后与对应活动的关系，并包含了报名活动后的面试状态。可以看作是活动报名表
 */
public class ActivityUser {
    private Integer id;

    //活动id
    private Integer activityId;

    //志愿者id
    private Integer userId;

    //面试状态id,没有具体的域对象映射，只在数据库中有记录
    private Integer activityUserStatusId;

    //评分，暂时未使用
    private Double star;

    //签到状态
    private Integer signInStatus;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getActivityUserStatusId() {
        return activityUserStatusId;
    }

    public void setActivityUserStatusId(Integer activityUserStatusId) {
        this.activityUserStatusId = activityUserStatusId;
    }

    public Double getStar() {
        return star;
    }

    public void setStar(Double star) {
        this.star = star;
    }

    public Integer getSignInStatus() {
        return signInStatus;
    }

    public void setSignInStatus(Integer signInStatus) {
        this.signInStatus = signInStatus;
    }
}