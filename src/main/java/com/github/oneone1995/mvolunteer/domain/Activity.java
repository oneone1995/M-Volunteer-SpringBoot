package com.github.oneone1995.mvolunteer.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Activity {
    private Integer id;

    private String addressProvince;

    private String addressCity;

    private String addressDistrict;

    private String addressStreet;

    private String address;

    private Double coordLong;

    private Double coordLat;

    //geohash字段，用于查找附近的活动
    @Getter
    @Setter
    private String geohash;

    private String serviceType;

    private String serviceObject;

    private String recruitTime;

    private Integer recruitPersonNumber;

    private String name;

    private String time;

    private String principalName;

    private String principalContact;

    private Integer activityStatusId;

    private Double workingHours;

    private Date timestamp;

    private String picture;

    private Integer code;

    private Integer organizationId;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddressProvince() {
        return addressProvince;
    }

    public void setAddressProvince(String addressProvince) {
        this.addressProvince = addressProvince;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressDistrict() {
        return addressDistrict;
    }

    public void setAddressDistrict(String addressDistrict) {
        this.addressDistrict = addressDistrict;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getCoordLong() {
        return coordLong;
    }

    public void setCoordLong(Double coordLong) {
        this.coordLong = coordLong;
    }

    public Double getCoordLat() {
        return coordLat;
    }

    public void setCoordLat(Double coordLat) {
        this.coordLat = coordLat;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceObject() {
        return serviceObject;
    }

    public void setServiceObject(String serviceObject) {
        this.serviceObject = serviceObject;
    }

    public String getRecruitTime() {
        return recruitTime;
    }

    public void setRecruitTime(String recruitTime) {
        this.recruitTime = recruitTime;
    }

    public Integer getRecruitPersonNumber() {
        return recruitPersonNumber;
    }

    public void setRecruitPersonNumber(Integer recruitPersonNumber) {
        this.recruitPersonNumber = recruitPersonNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public Integer getActivityStatusId() {
        return activityStatusId;
    }

    public void setActivityStatusId(Integer activityStatusId) {
        this.activityStatusId = activityStatusId;
    }

    public Double getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(Double workingHours) {
        this.workingHours = workingHours;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}