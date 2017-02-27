package com.github.oneone1995.mvolunteer.domain;

/**
 * Created by wangl on 2017/2/25.
 * Volunteer包装类，继承自VolunteerInfo，包装了志愿者的证书状态id
 */
public class VolunteerDetails extends VolunteerInfo{
    private Integer certificateStatusId;

    private Integer interviewStatus;

    private String activityName;

    public Integer getCertificateStatusId() {
        return certificateStatusId;
    }

    public void setCertificateStatusId(Integer certificateStatusId) {
        this.certificateStatusId = certificateStatusId;
    }

    public Integer getInterviewStatus() {
        return interviewStatus;
    }

    public void setInterviewStatus(Integer interviewStatus) {
        this.interviewStatus = interviewStatus;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }
}
