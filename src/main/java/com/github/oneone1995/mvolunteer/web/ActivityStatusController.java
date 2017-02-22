package com.github.oneone1995.mvolunteer.web;

import com.github.oneone1995.mvolunteer.config.result.ResultStatus;
import com.github.oneone1995.mvolunteer.domain.ActivityStatus;
import com.github.oneone1995.mvolunteer.model.ResultModel;
import com.github.oneone1995.mvolunteer.service.ActivityStatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by wangl on 2017/2/22.
 * 用于活动状态查询
 */
@RestController
@RequestMapping("/api/activityStatus")
public class ActivityStatusController {

    @Resource
    private ActivityStatusService activityStatusService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getActivityStatusById(
            @PathVariable Integer id
    ) {
        ActivityStatus activityStatus = activityStatusService.getActivityStatusById(id);
        if (activityStatus == null) {
            return new ResponseEntity<>(ResultModel.error(ResultStatus.ACTIVITY_STATUS_NOT_FOUNT), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ResultModel.ok(activityStatus), HttpStatus.OK);
    }
}
