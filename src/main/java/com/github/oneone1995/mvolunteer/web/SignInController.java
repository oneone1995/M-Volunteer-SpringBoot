package com.github.oneone1995.mvolunteer.web;

import com.github.oneone1995.mvolunteer.config.result.ResultStatus;
import com.github.oneone1995.mvolunteer.model.ResultModel;
import com.github.oneone1995.mvolunteer.service.SignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @Autowired
    private SignInService signInService;

    @PostMapping
    public ResponseEntity<?> signIn(
            @RequestParam(value = "code") Integer code
    ) {
        String result = signInService.signIn(code);
        if (result.equals("fail")) {
            return new ResponseEntity<>(ResultModel.error(ResultStatus.SIGN_IN_FAIL), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(ResultModel.ok(), HttpStatus.OK);
    }

}
