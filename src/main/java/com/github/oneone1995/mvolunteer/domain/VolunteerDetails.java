package com.github.oneone1995.mvolunteer.domain;

/**
 * Created by wangl on 2017/2/25.
 * Volunteer包装类，继承自VolunteerInfo，包装了志愿者的证书状态id
 */
public class VolunteerDetails extends VolunteerInfo{
    private Integer certificateStatusId;

    public Integer getCertificateStatusId() {
        return certificateStatusId;
    }

    public void setCertificateStatusId(Integer certificateStatusId) {
        this.certificateStatusId = certificateStatusId;
    }
}
