package com.zoro.springboot.constant;

import com.zoro.springboot.constant.ErrorCodeEnum;

import java.io.Serializable;

public class ResultRes<T> implements Serializable {
    private int code;

    private String message;

    private T data;

    public ResultRes() {
    }

    public ResultRes(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static <T> ResultRes success(T data) {
        return new ResultRes(ErrorCodeEnum.SUCCESS.getCode(), ErrorCodeEnum.SUCCESS.getMessage(), data);
    }

    public static <T> ResultRes systemError() {
        return new ResultRes(ErrorCodeEnum.ERROR.getCode(), ErrorCodeEnum.ERROR.getMessage(), null);
    }

    public static <T> ResultRes systemError(String errorCode) {
        return new ResultRes(ErrorCodeEnum.ERROR.getCode(), "系统错误:["+errorCode+"]", null);
    }

    public static <T> ResultRes paramError(String msg) {
        return new ResultRes(ErrorCodeEnum.PARAM_ERROR.getCode(), "参数错误:["+msg+"]", null);
    }


    public static <T> ResultRes buinessError(ErrorCodeEnum errorCodeEnum) {
        return new ResultRes(errorCodeEnum.getCode(), errorCodeEnum.getMessage(), null);
    }

    public static <T> ResultRes buinessError(int code,String message) {
        return new ResultRes(code, message, null);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
