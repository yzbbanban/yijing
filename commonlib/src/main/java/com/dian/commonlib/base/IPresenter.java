package com.dian.commonlib.base;

import android.content.Context;

/**
 * Created by kennysun on 2019/8/7.
 */
 public interface IPresenter<V extends IView>  {
        void attachView(V mvpView, Context context);
        V getMvpView();
}
