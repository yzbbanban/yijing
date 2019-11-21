package com.yjb.mvp.contract.home;


import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;

/**
 * Created by kennysun on 2019/8/7.
 */
public interface NewsViewIncContract {
    interface View extends IView {
        void getNVIcSuccess(Object o);
    }

    interface Presenter extends IPresenter<View> {
        void getList(String token, String id);
    }
}
