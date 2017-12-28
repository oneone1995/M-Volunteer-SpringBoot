package com.github.oneone1995.mvolunteer.web;

import com.github.oneone1995.mvolunteer.config.result.ResultStatus;
import com.github.oneone1995.mvolunteer.domain.HomeActivity;
import com.github.oneone1995.mvolunteer.domain.User;
import com.github.oneone1995.mvolunteer.model.ResultModel;
import com.github.oneone1995.mvolunteer.service.SearchService;
import com.github.oneone1995.mvolunteer.web.exception.GroupNotFoundException;
import com.github.oneone1995.mvolunteer.web.exception.UserNotFoundException;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wangl on 2017/2/20.
 * 搜索有关controller
 */
@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;


    @GetMapping("activity")
    public ResponseEntity<?> getActivitySearchResult(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows", defaultValue = "10") Integer rows,
            @RequestParam(value = "coordLong") double coordLong,
            @RequestParam(value = "coordLat") double coordLat,
            @RequestParam(value = "activityName") String activityName
    ) {
        PageInfo<HomeActivity> searchActivityPageInfo = searchService.getHomeActivityPageInfo(
                page, rows, coordLong, coordLat, activityName);
        if (searchActivityPageInfo == null) {
            return new ResponseEntity<>(ResultModel.error(ResultStatus.ACTIVITY_NOT_FOUNT), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ResultModel.ok(searchActivityPageInfo), HttpStatus.OK);
    }

    /**
     * 根据用户username搜索用户信息
     * 主要用在聊天中显示对方的头像等用户信息，环信用户体系和APP本身用户体系通过username关联
     * @param username 用户的username
     */
    @GetMapping("user")
    @PreAuthorize("hasRole('ROLE_VOL') or hasRole('ROLE_ORG')")
    public ResponseEntity<?> getUserSearchResult(
            @RequestParam(value = "username") String username) throws UserNotFoundException {
        return new ResponseEntity<>(ResultModel.ok(searchService.getUserByUsername(username)), HttpStatus.OK);
    }

    /**
     * 根据群组id返回群头像
     * app群组界面加载群组头像的接口，因为环信群组只能获得群id，群id和活动一一对应保证了接口的可用
     * @param groupId 环信群id
     */
    @GetMapping("group")
    @PreAuthorize("hasRole('ROLE_VOL') or hasRole('ROLE_ORG')")
    public ResponseEntity<?> getGroupAvatarSearchResult(
            @RequestParam(value = "groupId") String groupId
    ) throws GroupNotFoundException {
        return new ResponseEntity<>(ResultModel.ok(searchService.getGroupAvatar(groupId)), HttpStatus.OK);
    }
}
