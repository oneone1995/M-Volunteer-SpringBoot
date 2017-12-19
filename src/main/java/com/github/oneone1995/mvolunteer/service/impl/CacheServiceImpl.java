package com.github.oneone1995.mvolunteer.service.impl;

import com.github.oneone1995.mvolunteer.service.CacheService;
import com.github.oneone1995.mvolunteer.service.EasemobIMService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CacheServiceImpl implements CacheService {
    @Autowired
    private EasemobIMService easemobIMService;

    @Override
    public String getEasemobToken() {
        return easemobIMService.getEasemobToken();
    }
}
