package com.github.oneone1995.mvolunteer.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangl on 2017/2/22.
 * 用于活动状态查询
 */
@RestController
@RequestMapping("/api/activityStatus")
public class ActivityStatusController {

    @GetMapping("/{id}")
    public ResponseEntity<?> getActivityStatusById(
            @PathVariable Integer id
    ) {
        return null;
    }
}
