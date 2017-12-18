package com.github.oneone1995.mvolunteer.web.exception;

import java.io.Serializable;

/**
 * 添加用户至环信IM聊天群异常
 */
public class PutUserToEasemobGroupFailException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -7741427766627377881L;

    public PutUserToEasemobGroupFailException(String message) {
        super(message);
    }
}
