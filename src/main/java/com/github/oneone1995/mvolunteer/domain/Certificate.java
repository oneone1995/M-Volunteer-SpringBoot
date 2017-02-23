package com.github.oneone1995.mvolunteer.domain;

import java.util.Date;

public class Certificate {
    private Integer id;

    private Integer userId;

    private Integer certificateStatusId;

    private Date timestamp;

    private String address;

    private String receiver;

    private String receiverPhone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getCertificateStatusId() {
        return certificateStatusId;
    }

    public void setCertificateStatusId(Integer certificateStatusId) {
        this.certificateStatusId = certificateStatusId;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }
}