package com.github.oneone1995.mvolunteer.web;

import com.github.oneone1995.mvolunteer.config.result.ResultStatus;
import com.github.oneone1995.mvolunteer.domain.Certificate;
import com.github.oneone1995.mvolunteer.model.ResultModel;
import com.github.oneone1995.mvolunteer.service.CertificateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Created by wangl on 2017/2/23.
 */
@RestController
@RequestMapping("/api/certificate")
public class CertificateController {

    @Resource
    private CertificateService certificateService;

    @PostMapping
    @PreAuthorize("hasRole('ROLE_VOL')")
    public ResponseEntity<?> postCertificate(
            @RequestBody Certificate certificate
            ) {
        String result = certificateService.applyForCertificate(certificate);

        if (result.equals("ALREADY_ERROR"))
            return new ResponseEntity<>(ResultModel.error(ResultStatus.CERTIFICATE_IS_EXIST), HttpStatus.INTERNAL_SERVER_ERROR);

        if (result.equals("INSUFFICIENT_ERROR")) {
            return new ResponseEntity<>(ResultModel.error(ResultStatus.WORKING_HOURS_NOT_ENOUGH), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(ResultModel.ok(result), HttpStatus.OK);
    }

    @PutMapping
    @PreAuthorize("hasRole('ROLE_ORG')")
    public ResponseEntity<?> putCertificate(
            @RequestParam(value = "volunteerId") Integer volunteerId
    ) {
        boolean result = certificateService.modifyCertificateStatusByVolunteerId(volunteerId);

        if (!result) {
            return new ResponseEntity<Object>(ResultModel.error(ResultStatus.CERTIFICATE_STATUS_UPDATE_FAIL), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Object>(ResultModel.ok("update success"), HttpStatus.OK);
    }
}
