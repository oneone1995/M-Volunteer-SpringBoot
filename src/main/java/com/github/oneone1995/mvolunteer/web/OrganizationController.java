package com.github.oneone1995.mvolunteer.web;

import com.github.oneone1995.mvolunteer.config.result.ResultStatus;
import com.github.oneone1995.mvolunteer.domain.CustomUserDetails;
import com.github.oneone1995.mvolunteer.domain.OrganizationInfo;
import com.github.oneone1995.mvolunteer.mapper.OrganizationInfoMapper;
import com.github.oneone1995.mvolunteer.model.ResultModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by wangl on 2017/2/24.
 */
@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
    @Resource
    private OrganizationInfoMapper organizationInfoMapper;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ORG')")
    public ResponseEntity<?> getOrganizationById() {
        CustomUserDetails currentUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer id = currentUser.getId();
        OrganizationInfo organizationInfo = organizationInfoMapper.selectByPrimaryKey(id);

        if (organizationInfo == null) {
            return new ResponseEntity<>(ResultModel.error(ResultStatus.USER_NOT_FOUND), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ResultModel.ok(organizationInfo), HttpStatus.OK);
    }
}
