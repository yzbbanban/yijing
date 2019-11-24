package com.yjb.mvp.contract.home;


import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;

/**
 * Created by kennysun on 2019/8/7.
 */
public interface GetSignStautsContract {
    interface View extends IView {
        void getSignStatusSuccess(String msg);
    }

    interface Presenter extends IPresenter<View> {
        void getList(String token, String activity_id, String user_id);
    }
}
