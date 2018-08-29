package com.zoro.springboot.constant;

/**
 * @date 2018/8/20  14:55
 */
public class ResultRes<T> {
    private String resultCode;
    private String resultDec;
    private T data;

    private ResultRes(String result, String reason, T data) {
        this.resultCode = result;
        this.resultDec = reason;
        this.data = data;
    }

    public ResultRes(ErrorCodeEnum errCode) {
        this.resultCode = ResEnum.FAIL.toString();
        this.resultDec = errCode.toString();
    }

    public ResultRes(T data) {
        this.resultCode = ResEnum.SUCCESS.toString();
        this.data = data;
    }

    public ResultRes(ErrorCodeEnum errCode, T data) {
        this.resultCode = ResEnum.FAIL.toString();
        this.resultDec = errCode.toString();
        this.data = data;
    }

    public static ResultRes fail(String reason, String data) {
        return new ResultRes("500", reason, data);
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
