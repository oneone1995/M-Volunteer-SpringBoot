package com.github.oneone1995.mvolunteer.service.impl;

import com.github.oneone1995.mvolunteer.service.CacheService;
import com.github.oneone1995.mvolunteer.service.EasemobIMService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class CacheServiceImpl implements CacheService {
    //活动码在redis中的键名
    private static final String ACTIVITY_CODE_REDIS_KEY = "activity-code";

    @Autowired
    private EasemobIMService easemobIMService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String getEasemobToken() {
        return easemobIMService.getEasemobToken();
    }

    @Override
    public void putRandomActivityCode(String code) {
        log.info("put code {} into redis", code);

        redisTemplate
                .opsForList()
                .leftPush(ACTIVITY_CODE_REDIS_KEY, code);
    }

    @Override
    public String getRandomActivityCode() {
        String code = redisTemplate
                .opsForList()
                .rightPop(ACTIVITY_CODE_REDIS_KEY);

        log.info("get code {} from redis", code);
        return code;
    }
}
