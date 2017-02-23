package com.github.oneone1995.mvolunteer.domain;

import java.util.List;

/**
 * Created by wangl on 2017/2/21.
 * 继承自Activity
 * 用于活动详情的实体
 * 包含了该活动的id、发起组织、活动内容描述、招募时间、活动开始时间、活动地点、提供时长、
 * 活动联系人、联系人电话、服务类别、活动名字、与当地的距离、已报名的人数、已报名的志愿者
 */
public class ActivityDetails extends Activity{

    private String organization;

    //面试状态
    private String interviewStatus;

    //活动状态
    private String activityStatus;

    //与当地的距离
    private double distance;

    //已报名的人数
    private Integer recruitedPersonNumber;

    //是否已经报名或已经推荐
    private boolean isSignUpOrRecommend;

    private List<VolunteerInfo> volunteers;

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getInterviewStatus() {
        return interviewStatus;
    }

    public void setInterviewStatus(String interviewStatus) {
        this.interviewStatus = interviewStatus;
    }

    public String getActivityStatus() {
        return activityStatus;
    }

    public void setActivityStatus(String activityStatus) {
        this.activityStatus = activityStatus;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Integer getRecruitedPersonNumber() {
        return recruitedPersonNumber;
    }

    public void setRecruitedPersonNumber(Integer recruitedPersonNumber) {
        this.recruitedPersonNumber = recruitedPersonNumber;
    }

    public boolean isSignUpOrRecommend() {
        return isSignUpOrRecommend;
    }

    public void setSignUpOrRecommend(boolean signUpOrRecommend) {
        isSignUpOrRecommend = signUpOrRecommend;
    }

    public List<VolunteerInfo> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(List<VolunteerInfo> volunteers) {
        this.volunteers = volunteers;
    }
}
