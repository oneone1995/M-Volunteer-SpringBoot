package com.github.oneone1995.mvolunteer.web;

import com.github.oneone1995.mvolunteer.domain.User;
import com.github.oneone1995.mvolunteer.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by wangl on 2017/2/5.
 */
@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Resource
    private UserService userService;

    @GetMapping("/{id}")
    public User queryUser(@PathVariable Integer id){
        return userService.findUserById(id);
    }
}
