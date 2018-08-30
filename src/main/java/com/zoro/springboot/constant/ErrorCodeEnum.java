package com.zoro.springboot.constant;

public enum ErrorCodeEnum {

    SUCCESS(2000, "SUCCESS"),
    PARAM_ERROR(1400, "参数错误"),
    ERROR(5000, "请求失败，请联系客服");


    private int code;
    private String message;

    private ErrorCodeEnum(int code, String message) {
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

