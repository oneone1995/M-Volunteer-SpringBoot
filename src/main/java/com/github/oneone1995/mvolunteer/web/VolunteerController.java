package com.github.oneone1995.mvolunteer.web;

import com.github.oneone1995.mvolunteer.domain.CustomUserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangl on 2017/2/22.
 * 个人中心的基本信息接口
 */
@RestController
@RequestMapping("/api/volunteer")
public class VolunteerController {

    @GetMapping
    public ResponseEntity<?> getVolunteerById() {
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer id = currentUser.getId();
        return null;
    }
}
