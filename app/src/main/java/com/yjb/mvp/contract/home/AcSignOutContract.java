package com.yjb.mvp.contract.home;


import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;

/**
 * Created by kennysun on 2019/8/7.
 */
public interface AcSignOutContract {
    interface View extends IView {
        void getAcSignOutSuccess(String msg);
    }

    interface Presenter extends IPresenter<View> {
        void getList(String token, String fakeid, String activity_id);
    }
}
