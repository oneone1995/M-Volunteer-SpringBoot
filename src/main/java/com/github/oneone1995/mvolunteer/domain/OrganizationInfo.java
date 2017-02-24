package com.github.oneone1995.mvolunteer.domain;

import java.util.Date;

public class OrganizationInfo extends User{
    private Integer id;

    private String name;

    private String addressProvince;

    private String addressCity;

    private String addressDistrict;

    private String addressStreet;

    private String address;

    private String zipCode;

    private Date foundingDate;

    private Integer volunteerNumber;

    private String principalName;

    private String principalTelephone;

    private Integer principalTelephonePublic;

    private String avatar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Date getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(Date foundingDate) {
        this.foundingDate = foundingDate;
    }

    public Integer getVolunteerNumber() {
        return volunteerNumber;
    }

    public void setVolunteerNumber(Integer volunteerNumber) {
        this.volunteerNumber = volunteerNumber;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public String getPrincipalTelephone() {
        return principalTelephone;
    }

    public void setPrincipalTelephone(String principalTelephone) {
        this.principalTelephone = principalTelephone;
    }

    public Integer getPrincipalTelephonePublic() {
        return principalTelephonePublic;
    }

    public void setPrincipalTelephonePublic(Integer principalTelephonePublic) {
        this.principalTelephonePublic = principalTelephonePublic;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}