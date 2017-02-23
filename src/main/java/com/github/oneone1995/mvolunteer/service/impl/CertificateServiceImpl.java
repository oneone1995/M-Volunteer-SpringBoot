package com.github.oneone1995.mvolunteer.service.impl;

import com.github.oneone1995.mvolunteer.domain.Certificate;
import com.github.oneone1995.mvolunteer.domain.CustomUserDetails;
import com.github.oneone1995.mvolunteer.mapper.CertificateMapper;
import com.github.oneone1995.mvolunteer.mapper.VolunteerInfoMapper;
import com.github.oneone1995.mvolunteer.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wangl on 2017/2/23.
 */

@Service
public class CertificateServiceImpl implements CertificateService {
    @Autowired
    private CertificateMapper certificateMapper;

    @Autowired
    private VolunteerInfoMapper volunteerInfoMapper;

    @Override
    @Transactional
    public String applyForCertificate(Certificate certificate) {
        //获取当前用户
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //查询当前用户的工时
        if (volunteerInfoMapper.selectByPrimaryKey(currentUser.getId()).getWorkingHours() >= 60) {
            certificate.setUserId(currentUser.getId());
            return certificateMapper.insert(certificate) > 0 ? "SUCCESS": "ALREADY_ERROR";
        }
        return "INSUFFICIENT_ERROR";
    }
}
