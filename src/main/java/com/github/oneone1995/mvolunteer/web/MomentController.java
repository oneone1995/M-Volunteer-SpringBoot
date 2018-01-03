package com.github.oneone1995.mvolunteer.web;

import com.github.oneone1995.mvolunteer.domain.moment.Moment;
import com.github.oneone1995.mvolunteer.domain.moment.MomentComment;
import com.github.oneone1995.mvolunteer.model.ResultModel;
import com.github.oneone1995.mvolunteer.service.MomentService;
import com.github.oneone1995.mvolunteer.web.exception.MomentCommentCreateFailException;
import com.github.oneone1995.mvolunteer.web.exception.MomentCommentNotFoundException;
import com.github.oneone1995.mvolunteer.web.exception.MomentCreateFailException;
import com.github.oneone1995.mvolunteer.web.exception.MomentNotFoundException;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/moment")
public class MomentController {

    private final MomentService momentService;

    @Autowired
    public MomentController(MomentService momentService) {
        this.momentService = momentService;
    }


    @GetMapping
    @PreAuthorize("hasRole('ROLE_VOL') or hasRole('ROLE_ORG')")
    public ResponseEntity<?> getMoment(
            @RequestParam(value = "page") Integer page,
            @RequestParam(value = "rows") Integer rows
    ) throws MomentNotFoundException {
        PageInfo<Moment> momentPageInfo = momentService.getMoment(page, rows);
        return new ResponseEntity<>(ResultModel.ok(momentPageInfo), HttpStatus.OK);
    }

    @GetMapping("/{momentId}")
    @PreAuthorize("hasRole('ROLE_VOL') or hasRole('ROLE_ORG')")
    public ResponseEntity<?> getMomentById(
            @PathVariable("momentId") Integer momentId
    ) throws MomentNotFoundException {
        Moment moment = momentService.getMomentById(momentId);
        return new ResponseEntity<>(ResultModel.ok(moment), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_VOL') or hasRole('ROLE_ORG')")
    public ResponseEntity<?> postMoment(@RequestBody Moment moment) throws MomentCreateFailException {
        momentService.createMoment(moment);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(moment.getId())
                .toUri();


        return ResponseEntity
                .created(location)
                .body(ResultModel.ok("创建动态成功"));
    }

    /**
     * 发布动态评论
     * @param momentId 动态id
     */
    @PostMapping("/{id}/comments")
    @PreAuthorize("hasRole('ROLE_VOL') or hasRole('ROLE_ORG')")
    public ResponseEntity<?> postMomentComment(
            @PathVariable("id") Integer momentId,
            @RequestBody MomentComment comment) throws MomentCommentCreateFailException {
        momentService.createMomentComment(momentId, comment);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{commentId}")
                .buildAndExpand(comment.getMomentId())
                .toUri();


        return ResponseEntity
                .created(location)
                .body(ResultModel.ok("评论成功"));
    }

    /**
     * 获取动态的评论
     * @param momentId 动态id
     */
    @GetMapping("/{id}/comments")
    @PreAuthorize("hasRole('ROLE_VOL') or hasRole('ROLE_ORG')")
    public ResponseEntity<?> getMomentComments(@PathVariable("id") Integer momentId) throws MomentCommentNotFoundException {
        List<MomentComment> momentComments = momentService.getMomentCommentsByMomentId(momentId);

        return new ResponseEntity<>(ResultModel.ok(momentComments), HttpStatus.OK);
    }

    /**
     * 获取某动态下的某条评论
     * @param momentId 动态id
     * @param commentId 评论id
     */
    @GetMapping("/{id}/comments/{commentId}")
    public ResponseEntity<?> getMomentComment(
            @PathVariable("id") Integer momentId,
            @PathVariable("commentId") Integer commentId) throws MomentCommentNotFoundException {

        MomentComment momentComment = momentService.getMomentCommentById(commentId);
        return new ResponseEntity<>(ResultModel.ok(momentComment), HttpStatus.OK);
    }
}
