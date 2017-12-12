package com.github.oneone1995.mvolunteer.web.exception;

import java.io.Serializable;

/**
 * 动态查询不到异常
 */
public class MomentNotFoundException extends Exception implements Serializable {
    private static final long serialVersionUID = 9114632605738988682L;

    public MomentNotFoundException(String message) {
        super(message);
    }
}
