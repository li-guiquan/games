package com.quan.games.common;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class resultResponse {
    private long total;
    private Object date;
    private String code;
    private String msg;

    private resultResponse(long total, Object date, String code, String msg) {
        this.total = total;
        this.date = date;
        this.code = code;
        this.msg = msg;
    }

    private resultResponse(Object date) {
        this.date = date;
        this.code = resultMsg.SUCCESS.getCode();
        this.msg = resultMsg.SUCCESS.getMsg();
    }

    private resultResponse(long total, Object date) {
        this.total = total;
        this.date = date;
        this.code = resultMsg.SUCCESS.getCode();
        this.msg = resultMsg.SUCCESS.getMsg();
    }

    private resultResponse(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private resultResponse() {
        this.code = resultMsg.SUCCESS.getCode();
        this.msg = resultMsg.SUCCESS.getMsg();
    }

    /**
     * @param total
     * @param date
     * @param code
     * @param msg
     * @return 返回成功，包含total、code、msg、data
     */
    public static resultResponse success(long total, Object date, String code, String msg) {
        return new resultResponse(total, date, code, msg);
    }

    /**
     * @param total
     * @param date
     * @return 返回成功，包含total，date
     */
    public static resultResponse success(long total, Object date) {
        return new resultResponse(total, date);
    }

    /**
     * @param date
     * @return 返回成功，包含date
     */
    public static resultResponse success(Object date) {
        return new resultResponse(date);
    }

    /**
     * @return 返回成功
     */
    public static resultResponse success() {
        return new resultResponse("");
    }

    /***
     *
     * @return返回失败
     */
    public static resultResponse error() {
        return new resultResponse("");
    }

    /**
     * @param total
     * @param date
     * @return 返回失败 ，包含total，date
     */
    public static resultResponse error(long total, Object date) {
        return new resultResponse(total, date);
    }

}
