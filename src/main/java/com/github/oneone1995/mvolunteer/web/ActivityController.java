package com.github.oneone1995.mvolunteer.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangl on 2017/2/18.
 */
@RestController
@RequestMapping("/api/activity")
public class ActivityController {

    @GetMapping
    public ResponseEntity<?> getActivities(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "coordLong") double coordLong,
            @RequestParam(value = "coordLat") double coordLat) {
        //todo 返回活动信息
        return null;
    }
}
