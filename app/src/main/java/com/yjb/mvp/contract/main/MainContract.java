package com.yjb.mvp.contract.main;


import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;

/**
 * Created by kennysun on 2019/8/7.
 */

public interface MainContract {
    interface View extends IView {
        void getUserSuccess();
    }
    interface Presenter extends IPresenter<View> {
        void getUser();
        void updateGroupAndFriendInfo();
    }
}
