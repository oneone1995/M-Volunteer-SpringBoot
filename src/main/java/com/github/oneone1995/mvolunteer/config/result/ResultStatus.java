package com.github.oneone1995.mvolunteer.config.result;

/**
 * Created by wangl on 2017/2/7.
 * 自定义请求状态码
 */
public enum ResultStatus {
    SUCCESS(200, "成功"),
    USERNAME_OR_PASSWORD_ERROR(-1001, "用户名或密码错误"),
    USER_NOT_FOUND(-1002, "用户不存在"),
    USER_NOT_LOGIN(-1003, "用户未登录"),
    ACTIVITY_NOT_FOUNT(-1004, "活动不存在"),
    FILE_IS_EMPTY(-1005, "文件为空"),
    FILE_UPLOAD_ERROR(-1006, "文件上传失败"),
    SIGN_IN_FAIL(-1007, "签到失败"),
    ACTIVITY_CREATE_FAIL(-1008, "创建活动失败"),
    ACTIVITY_STATUS_NOT_FOUNT(-1009, "活动状态不存在"),
    CERTIFICATE_IS_EXIST(-10010, "证书已经申请过啦"),
    WORKING_HOURS_NOT_ENOUGH(-10011, "工时不够"),
    SIGN_UP_FAIL(-10012, "报名失败"),
    CANCEL_FAIL(-10013, "取消失败"),
    ACTIVITY_STATUS_UPDATE_FAIL(-10014,"活动状态更新失败"),
    ACTIVITY_STATUS_IMMUTABLE(-10015, "活动状态不可更改"),
    ORGANIZATION_HAS_NO_VOLUNTEER(-10016, "组织没有志愿者加入"),
    CERTIFICATE_STATUS_UPDATE_FAIL(-10017, "证书状态更新失败"),
    INTERVIEW_IS_NULL(-10018, "面试列表为空"),
    INTERVIEW_STATUS_UPDATE_FAIL(-10019, "面试状态修改失败"),
    MOMENT_NOT_FOUNT(-10020, "动态不存在"),
    MOMENT_CREATE_FAIL(-10021, "创建动态失败");

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回信息描述
     */
    private String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
