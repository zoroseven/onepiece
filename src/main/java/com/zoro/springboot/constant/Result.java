package com.zoro.springboot.constant;

/**
 * @date 2018/8/20  14:55
 */
public class Result<T> {
    private String resultCode;
    private String resultDec;
    private T data;

    public Result(ErrorCodeEnum errCode) {
        this.resultCode = ResEnum.FAIL.toString();
        this.resultDec = errCode.toString();
    }

    public Result(T data) {
        this.resultCode = ResEnum.SUCCESS.toString();
        this.data = data;
    }

    public Result(ErrorCodeEnum errCode,T data) {
        this.resultCode = ResEnum.FAIL.toString();
        this.resultDec = errCode.toString();
        this.data = data;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultDec() {
        return resultDec;
    }

    public void setResultDec(String resultDec) {
        this.resultDec = resultDec;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
