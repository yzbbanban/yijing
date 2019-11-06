package com.huohuo.net;

/**
 * Created by kennysun on 2019/8/28.
 */

public class HuoHuoErrorStatus {
    /**
     * 登录失败，启用滑动验证
     */
    public static int PERSONAL_USER_ACCOUNT_LOGIN_GEETEST_ERROR = 5002;

    /**
     * 登录成功，用户在另一个设备进行登录
     */
    public static int LOGIN_SUCCESS_IN_OTHER_DEVICE = 5003;

    /**
     * 手机号未注册
     */
    public static int USER_NOT_EXISTS_ERROR = 5004;
    /**
     * 验证码错误
     */
    public static int SMS_CODE_ERROR = 5006;
    /**
     * 手机号已注册
     */
    public static int USER_EXISTS_ERROR = 5005;
    /**
     * 用户没有设置登录密码
     */
    public static int USER_NOT_SET_LOGIN_PWD = 5008;
    /**
     * 用户支付密码被冻结
     */
    public static int USER_PAY_PWD_FORZEN = 5010;
    /**
     * 时间戳错误
     */
    public static int TIMESTAMP_ERROR = 5011;
    /**
     * 签名失败
     */
    public static int SIGN_FALL_ERROR = 5012;

    /**
     * 数据库操作失败
     */
    public static int DAO_ERROR = 5013;
}
