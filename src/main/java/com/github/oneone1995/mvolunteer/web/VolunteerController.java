package com.github.oneone1995.mvolunteer.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.oneone1995.mvolunteer.config.result.ResultStatus;
import com.github.oneone1995.mvolunteer.domain.ActivityDetails;
import com.github.oneone1995.mvolunteer.domain.CustomUserDetails;
import com.github.oneone1995.mvolunteer.domain.VolunteerInfo;
import com.github.oneone1995.mvolunteer.model.ResultModel;
import com.github.oneone1995.mvolunteer.service.ActivityService;
import com.github.oneone1995.mvolunteer.service.VolunteerService;
import com.github.pagehelper.PageInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangl on 2017/2/22.
 * 个人中心的基本信息接口
 */
@RestController
@RequestMapping("/api/volunteer")
public class VolunteerController {

    @Resource
    private VolunteerService volunteerService;

    @Resource
    private ActivityService activityService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_VOL')")
    public ResponseEntity<?> getVolunteerById() {
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer id = currentUser.getId();
        VolunteerInfo volunteerInfo = volunteerService.getVolunteerById(id);

        if (volunteerInfo == null) {
            return new ResponseEntity<>(ResultModel.error(ResultStatus.USER_NOT_FOUND), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ResultModel.ok(volunteerInfo), HttpStatus.OK);
    }

    @GetMapping("/activities")
    @PreAuthorize("hasRole('ROLE_VOL')")
    public ResponseEntity<?> getActivitiesOfCurrentUser(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows
    ) {
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer id = currentUser.getId();

        PageInfo<ActivityDetails> activityDetailsPageInfo = activityService.getActivityPageInfoByVolunteerId(
                page, rows, id);
        if (activityDetailsPageInfo == null) {
            return new ResponseEntity<>(ResultModel.error(ResultStatus.ACTIVITY_NOT_FOUNT), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(ResultModel.ok(activityDetailsPageInfo), HttpStatus.OK);
    }
}
