package com.github.oneone1995.mvolunteer.web.exception;

import java.io.Serializable;

/**
 * 发送消息失败
 */
public class SendEasemobMessageFailException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -5920696942962007296L;

    public SendEasemobMessageFailException(String message) {
        super(message);
    }
}
