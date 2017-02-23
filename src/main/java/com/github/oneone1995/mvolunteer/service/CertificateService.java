package com.github.oneone1995.mvolunteer.service;

import com.github.oneone1995.mvolunteer.domain.Certificate;

/**
 * Created by wangl on 2017/2/23.
 * 证书申请的业务接口
 */
public interface CertificateService {
    //申请证书
    boolean applyForCertificate(Certificate certificate);
}
