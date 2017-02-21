package com.github.oneone1995.mvolunteer.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangl on 2017/2/20.
 */
@RestController
@RequestMapping("/api/signin")
public class SignInController {


    @PostMapping
    public ResponseEntity<?> signIn(
            @RequestParam(value = "code") String code
    ) {
        return null;
    }

}
