package com.github.oneone1995.mvolunteer.domain;

/**
 * Created by wangl on 2017/2/18.
 * 首页展示的活动列表对应的实体类
 * 包含了id，距离（km），区，已经招募人数，总人数，图片的地址，活动名字
 */
public class HomeActivity {
    private Integer id;

    private double distance;

    private String district;

    private Integer recruitedPersonNumber;

    private Integer recruitPersonNumber;

    private String picture;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Integer getRecruitedPersonNumber() {
        return recruitedPersonNumber;
    }

    public void setRecruitedPersonNumber(Integer recruitedPersonNumber) {
        this.recruitedPersonNumber = recruitedPersonNumber;
    }

    public Integer getRecruitPersonNumber() {
        return recruitPersonNumber;
    }

    public void setRecruitPersonNumber(Integer recruitPersonNumber) {
        this.recruitPersonNumber = recruitPersonNumber;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
