package com.github.oneone1995.mvolunteer.web.exception;

import java.io.Serializable;

/**
 * 创建评论失败异常
 */
public class MomentCommentCreateFailException extends Exception implements Serializable {
    private static final long serialVersionUID = -3531854142205971204L;

    public MomentCommentCreateFailException(String message) {
        super(message);
    }
}
