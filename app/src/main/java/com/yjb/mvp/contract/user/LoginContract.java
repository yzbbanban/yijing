package com.yjb.mvp.contract.user;


import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;

/**
 * Created by kennysun on 2019/8/7.
 */

public interface LoginContract {
    interface View extends IView {
        void getCodeSuccess();
        void registNever();
    }

    interface Presenter extends IPresenter<View> {
        void getCode(String account);
    }
}
