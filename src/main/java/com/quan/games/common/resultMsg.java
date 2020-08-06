package com.quan.games.common;

public enum resultMsg {
    SUCCESS("0", "成功"),
    ERROR("-1", "失败"),
    EXCEPTION1("10001", "1.请求异常"),
    EXCEPTION2("10002", "2.SQL异常"),
    EXCEPTION3("10003", "3."),
    EXCEPTION4("10004", "4.");

    private String code;
    private String msg;

    private resultMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
