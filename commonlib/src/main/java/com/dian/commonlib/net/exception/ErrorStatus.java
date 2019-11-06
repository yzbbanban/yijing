package com.dian.commonlib.net.exception;

/**
 * Created by kennysun on 2019/8/7.
 */

public class ErrorStatus {
    /**
     * 响应成功
     */
    public static int SUCCESS = 200;

    /**
     * 未知错误
     */
    public static int UNKNOWN_ERROR = 1002;

    /**
     * 服务器内部错误
     */
    public static int SERVER_ERROR = 1003;

    /**
     * 网络连接超时
     */
    public static int NETWORK_ERROR = 1004;

    /**
     * API解析异常（或者第三方数据结构更改）等其他异常
     */
    public static int API_ERROR = 1005;
}
