package com.yjb.mvp.contract.home;


import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;
import com.yjb.mvp.model.bean.JFInfo;

/**
 * Created by kennysun on 2019/8/7.
 */
public interface JFinfoContract {
    interface View extends IView {
        void getJFInfoSuccess(JFInfo msg);
    }

    interface Presenter extends IPresenter<View> {
        void getList(String token, String user_id);
    }
}
