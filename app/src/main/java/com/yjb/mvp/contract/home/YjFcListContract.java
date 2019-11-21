package com.yjb.mvp.contract.home;


import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;
import com.yjb.mvp.model.bean.FengcaiList;

/**
 * Created by kennysun on 2019/8/7.
 */
public interface YjFcListContract {
    interface View extends IView {
        void getYjFcListSuccess(FengcaiList fengcaiList);
    }

    interface Presenter extends IPresenter<View> {
        void getList(String token, String page, String size);
    }
}
