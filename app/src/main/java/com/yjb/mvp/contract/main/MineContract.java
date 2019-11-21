package com.yjb.mvp.contract.main;


import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;

/**
 * Created by kennysun on 2019/8/7.
 */

public interface MineContract {
    interface View extends IView {
        void loginOut();
    }

    interface Presenter extends IPresenter<View> {
        void loginOut();
    }
}
