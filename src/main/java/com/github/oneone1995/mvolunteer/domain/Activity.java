package com.github.oneone1995.mvolunteer.domain;

import java.util.List;

/**
 * Created by wangl on 2017/2/21.
 * 用于活动详情的实体
 * 包含了该活动的id、发起组织、活动内容描述、招募时间、活动开始时间、活动地点、提供时长、活动联系人、联系人电话、服务类别、已报名的志愿者
 * 活动名字、与当地的距离、已报名的人数和总人数由前一个页面传入
 */
public class Activity {

    private Integer id;

    private String organization;

    private String description;

    private String recruitTime;

    private String time;

    private String address;

    private double workingHours;

    private String principalName;

    private String principalContact;

    private String serviceType;

    private List<VolunteerInfo> volunteers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecruitTime() {
        return recruitTime;
    }

    public void setRecruitTime(String recruitTime) {
        this.recruitTime = recruitTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(double workingHours) {
        this.workingHours = workingHours;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getPrincipalContact() {
        return principalContact;
    }

    public void setPrincipalContact(String principalContact) {
        this.principalContact = principalContact;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public List<VolunteerInfo> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(List<VolunteerInfo> volunteers) {
        this.volunteers = volunteers;
    }
}
