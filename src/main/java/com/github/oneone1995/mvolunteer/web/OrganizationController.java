package com.github.oneone1995.mvolunteer.web;

import com.github.oneone1995.mvolunteer.config.result.ResultStatus;
import com.github.oneone1995.mvolunteer.domain.ActivityDetails;
import com.github.oneone1995.mvolunteer.domain.CustomUserDetails;
import com.github.oneone1995.mvolunteer.domain.OrganizationInfo;
import com.github.oneone1995.mvolunteer.domain.VolunteerDetails;
import com.github.oneone1995.mvolunteer.model.ResultModel;
import com.github.oneone1995.mvolunteer.service.ActivityService;
import com.github.oneone1995.mvolunteer.service.OrganizationService;
import com.github.oneone1995.mvolunteer.service.VolunteerService;
import com.github.pagehelper.PageInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by wangl on 2017/2/24.
 */
@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
    @Resource
    private OrganizationService organizationService;

    @Resource
    private ActivityService activityService;

    @Resource
    private VolunteerService volunteerService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ORG')")
    public ResponseEntity<?> getOrganizationById() {
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer id = currentUser.getId();
        OrganizationInfo organizationInfo = organizationService.getOrganizationInfoBy(id);

        if (organizationInfo == null) {
            return new ResponseEntity<>(ResultModel.error(ResultStatus.USER_NOT_FOUND), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ResultModel.ok(organizationInfo), HttpStatus.OK);
    }

    @GetMapping("/activities")
    @PreAuthorize("hasRole('ROLE_ORG')")
    public ResponseEntity<?> getActivitiesOfCurrentUser(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows
    ) {
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer id = currentUser.getId();

        PageInfo<ActivityDetails> activityDetailsPageInfo = activityService.getActivityPageInfoByOrganizationId(
                page, rows, id);

        if (activityDetailsPageInfo == null) {
            return new ResponseEntity<>(ResultModel.error(ResultStatus.ACTIVITY_NOT_FOUNT), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ResultModel.ok(activityDetailsPageInfo), HttpStatus.OK);
    }

    @GetMapping("/volunteers")
    @PreAuthorize("hasRole('ROLE_ORG')")
    public ResponseEntity<?> getVolunteersOfCurrentOrg(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows
    ) {
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer id = currentUser.getId();

        PageInfo<VolunteerDetails> volunteerDetailsPageInfo = volunteerService.getVolunteerListPageInfoByOrganizationId(
                page, rows, id);

        if (volunteerDetailsPageInfo == null) {
            return new ResponseEntity<Object>(ResultModel.error(ResultStatus.ORGANIZATION_HAS_NO_VOLUNTEER), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(ResultModel.ok(volunteerDetailsPageInfo), HttpStatus.OK);
    }
}
