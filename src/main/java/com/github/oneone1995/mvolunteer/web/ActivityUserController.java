package com.github.oneone1995.mvolunteer.web;

import com.github.oneone1995.mvolunteer.config.result.ResultStatus;
import com.github.oneone1995.mvolunteer.domain.ActivityUser;
import com.github.oneone1995.mvolunteer.model.ResultModel;
import com.github.oneone1995.mvolunteer.service.ActivityUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by wangl on 2017/2/23.
 */
@RestController
@RequestMapping("/api/activityUser")
public class ActivityUserController {

    @Resource
    private ActivityUserService activityUserService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_VOL')")
    public ResponseEntity<?> postActivityUser(
            @RequestBody ActivityUser activityUser
            ) {
        boolean result = activityUserService.signUpActivity(activityUser);

        if (!result) {
            return new ResponseEntity<Object>(ResultModel.error(ResultStatus.SIGN_UP_FAIL), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Object>(ResultModel.ok("报名成功"), HttpStatus.OK);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ROLE_VOL')")
    public ResponseEntity<?> deleteActivityUser(
            @RequestParam("activityId") Integer activityId
    ) {
        boolean result = activityUserService.cancelActivityByActivityId(activityId);

        if (!result) {
            return new ResponseEntity<Object>(ResultModel.error(ResultStatus.CANCEL_FAIL), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Object>(ResultModel.ok("取消成功"), HttpStatus.OK);
    }
}
