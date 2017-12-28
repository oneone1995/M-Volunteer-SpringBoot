package com.github.oneone1995.mvolunteer.web.exception;

import java.io.Serializable;

/**
 * 群组未找到异常
 */
public class GroupNotFoundException extends Exception implements Serializable {
    private static final long serialVersionUID = -6741308916421766984L;

    public GroupNotFoundException(String message) {
        super(message);
    }
}
