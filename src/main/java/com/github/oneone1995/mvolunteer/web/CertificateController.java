package com.github.oneone1995.mvolunteer.web;

import com.github.oneone1995.mvolunteer.domain.Certificate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Created by wangl on 2017/2/23.
 */
@RestController
@RequestMapping("/api/certificate")
public class CertificateController {

    @PostMapping
    @PreAuthorize("hasRole('ROLE_VOL')")
    public ResponseEntity<?> postCertificate(
            @RequestBody Certificate certificate
            ) {
        return null;
    }
}
