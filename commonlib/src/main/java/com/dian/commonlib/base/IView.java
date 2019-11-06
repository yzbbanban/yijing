package com.dian.commonlib.base;

/**
 * Created by kennysun on 2019/8/7.
 */

public interface IView {
    void showLoading(boolean show);

    void onError(Object msg, int code);

    //接口请求完毕
    void onComplete();
}
