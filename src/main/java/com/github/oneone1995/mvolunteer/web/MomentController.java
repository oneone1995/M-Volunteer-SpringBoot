package com.github.oneone1995.mvolunteer.web;

import com.github.oneone1995.mvolunteer.domain.moment.Moment;
import com.github.oneone1995.mvolunteer.model.ResultModel;
import com.github.oneone1995.mvolunteer.service.MomentService;
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
}
