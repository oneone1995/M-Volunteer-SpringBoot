package com.github.oneone1995.mvolunteer.web.exception;

import java.io.Serializable;

/**
 * 环信群组创建失败的异常
 */
public class EasemobGroupCreateFailException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -8203281680375663789L;

    public EasemobGroupCreateFailException(String message) {
        super(message);
    }
}
