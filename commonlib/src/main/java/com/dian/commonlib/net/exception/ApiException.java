package com.dian.commonlib.net.exception;

/**
 * Created by kennysun on 2019/8/7.
 */

public class ApiException extends RuntimeException {
    int code;
    String msg = "";

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }
    public ApiException(String message, int code) {
        super(new Throwable(message));
        this.code = code;
        this.msg = message;
    }

}
