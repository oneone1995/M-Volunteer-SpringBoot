package com.github.oneone1995.mvolunteer.web;

import com.github.oneone1995.mvolunteer.config.result.ResultStatus;
import com.github.oneone1995.mvolunteer.domain.ActivityUser;
import com.github.oneone1995.mvolunteer.domain.VolunteerDetails;
import com.github.oneone1995.mvolunteer.model.ResultModel;
import com.github.oneone1995.mvolunteer.service.ActivityUserService;
import com.github.pagehelper.PageInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by wangl on 2017/2/23.
 * 志愿者活动报名、取消报名，志愿组织面试管理相关接口
 */
@RestController
@RequestMapping("/api/activityUser")
public class ActivityUserController {

    @Resource
    private ActivityUserService activityUserService;

    /**
     * 分页获取面试管理列表接口,该API接口仅志愿组织(ROLE_ORG)有权访问
     * @see com.github.oneone1995.mvolunteer.service.ActivityUserService 返回具体值参见业务接口
     * @param page 页数
     * @param rows 每页的条数
     */
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ORG')")
    public ResponseEntity<?> getActivityUser(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows
    ) {
        PageInfo<VolunteerDetails> interviewList = activityUserService.getInterviewList(page, rows);

        if (interviewList == null) {
            return new ResponseEntity<Object>(ResultModel.error(ResultStatus.INTERVIEW_IS_NULL), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Object>(ResultModel.ok(interviewList), HttpStatus.OK);
    }

    /**
     * 报名活动接口，该API接口仅志愿者有权(ROLE_VOL)访问
     * @see com.github.oneone1995.mvolunteer.domain.ActivityUser
     * @param activityUser 活动用户表对应的实体
     */
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

    /**
     * 取消报名接口，该API接口仅志愿者有权(ROLE_VOL)访问
     * @param activityId 需要取消的活动id
     */
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

    /**
     * 修改面试状态接口，该API接口仅志愿组织(ROLE_ORG)有权访问
     * @param id 活动报名表id
     * @param volunteerName 修改的志愿者username
     * @param activityGroupId 活动对应的群组id
     * @param activityUserStatusId 面试状态id
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ORG')")
    public ResponseEntity<?> putActivityUser(
            @PathVariable Integer id,
            @RequestParam(value = "volunteerName") String volunteerName,
            @RequestParam(value = "groupId") String activityGroupId,
            @RequestParam(value = "activityUserStatusId") Integer activityUserStatusId
    ) {
        boolean result = activityUserService.modifyInterviewStatus(id, volunteerName, activityGroupId, activityUserStatusId);

        if (!result) {
            return new ResponseEntity<Object>(ResultModel.error(ResultStatus.INTERVIEW_STATUS_UPDATE_FAIL), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Object>(ResultModel.ok("修改面试状态成功"), HttpStatus.OK);
    }
}
