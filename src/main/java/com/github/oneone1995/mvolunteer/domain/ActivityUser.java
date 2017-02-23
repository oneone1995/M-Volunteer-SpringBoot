package com.github.oneone1995.mvolunteer.domain;

public class ActivityUser {
    private Integer id;

    private Integer activityId;

    private Integer userId;

    private Integer activityUserStatusId;

    private Double star;

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