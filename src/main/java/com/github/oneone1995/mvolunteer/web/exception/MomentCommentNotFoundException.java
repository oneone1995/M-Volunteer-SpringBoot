package com.github.oneone1995.mvolunteer.web.exception;

import java.io.Serializable;

/**
 * 动态评论未找到异常
 */
public class MomentCommentNotFoundException extends Exception implements Serializable {
    private static final long serialVersionUID = 5621312628252385352L;

    public MomentCommentNotFoundException(String message) {
        super(message);
    }
}
