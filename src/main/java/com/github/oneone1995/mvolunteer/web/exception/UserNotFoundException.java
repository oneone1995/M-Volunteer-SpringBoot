package com.github.oneone1995.mvolunteer.web.exception;

import java.io.Serializable;

/**
 * 用户找不到异常
 */
public class UserNotFoundException extends Exception implements Serializable {
    private static final long serialVersionUID = 4051047151187780044L;

    public UserNotFoundException(String message) {
        super(message);
    }
}
