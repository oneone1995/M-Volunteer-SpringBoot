package com.github.oneone1995.mvolunteer.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangl on 2017/2/20.
 * 搜索有关controller
 */
@RestController("/api/search")
public class SearchController {


    @GetMapping("activity/{activityname}")
    public ResponseEntity<?> getActivitySearchResult(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @PathVariable String activityName
    ) {
        return null;
    }
}
