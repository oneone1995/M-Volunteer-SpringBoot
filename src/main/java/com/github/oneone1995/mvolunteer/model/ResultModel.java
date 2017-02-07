package com.github.oneone1995.mvolunteer.model;

import com.github.oneone1995.mvolunteer.config.Result.ResultStatus;

/**
 * Created by wangl on 2017/2/7.
 * 自定义的返回结果实体model
 */
public class ResultModel {
    /**
     * 返回码
     */
    private int code;

    /**
     * 返回信息描述
     */
    private String message;

    /**
     * 返回数据
     */
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    //提供几种构造方法
    public ResultModel(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultModel(ResultStatus resultStatus) {
        this.code = resultStatus.getCode();
        this.message = resultStatus.getMessage();
        this.data = "";
    }

    public ResultModel(ResultStatus resultStatus, Object data) {
        this.code = resultStatus.getCode();
        this.message = resultStatus.getMessage();
        this.data = data;
    }

    //请求成功的方法

    /**
     * 有数据返回时的请求成功方法
     * @param data  请求成功的数据
     * @return  自定义的ResultModel
     */
    public static ResultModel ok(Object data) {
        return new ResultModel(ResultStatus.SUCCESS, data);
    }

    /**
     * 无数据返回时的请求成功方法
     * @return  自定义的ResultModel
     */
    public static ResultModel ok() {
        return new ResultModel(ResultStatus.SUCCESS);
    }

    //请求失败的方法

    /**
     * 请求失败时
     * @param error 错误状态
     * @return  自定义的ResultModel
     */
    public static ResultModel error(ResultStatus error) {
        return new ResultModel(error);
    }
}
