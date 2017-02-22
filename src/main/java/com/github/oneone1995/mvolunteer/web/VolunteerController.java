package com.github.oneone1995.mvolunteer.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.oneone1995.mvolunteer.config.result.ResultStatus;
import com.github.oneone1995.mvolunteer.domain.CustomUserDetails;
import com.github.oneone1995.mvolunteer.domain.VolunteerInfo;
import com.github.oneone1995.mvolunteer.model.ResultModel;
import com.github.oneone1995.mvolunteer.service.VolunteerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by wangl on 2017/2/22.
 * 个人中心的基本信息接口
 */
@RestController
@RequestMapping("/api/volunteer")
public class VolunteerController {

    @Resource
    private VolunteerService volunteerService;

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
}
