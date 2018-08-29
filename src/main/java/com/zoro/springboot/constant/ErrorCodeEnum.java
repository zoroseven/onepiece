package com.zoro.springboot.constant;

/**
 * @date 2018/8/20  14:59
 */
public enum  ErrorCodeEnum {
    SYS_ERROR("系统错误");

    ErrorCodeEnum(String errorCode){
        this.msg=errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String msg;
}
