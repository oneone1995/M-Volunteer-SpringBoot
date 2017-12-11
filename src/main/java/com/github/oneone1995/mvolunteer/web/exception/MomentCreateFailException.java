package com.github.oneone1995.mvolunteer.web.exception;

import java.io.Serializable;

/**
 * 圈子动态创建失败异常
 */
public class MomentCreateFailException extends Exception implements Serializable {
    private static final long serialVersionUID = 6653220248764028535L;

    public MomentCreateFailException(String message) {
        super(message);
    }
}
