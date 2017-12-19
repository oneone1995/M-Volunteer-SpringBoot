package com.github.oneone1995.mvolunteer.web.exception;

import java.io.Serializable;

/**
 * 生成环信token失败异常
 */
public class GenerateEasemobTokenFailException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -6497756186257516493L;

    public GenerateEasemobTokenFailException(String message) {
        super(message);
    }
}
