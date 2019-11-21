package com.huohuo.mvp.contract.home;


import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;
import com.huohuo.mvp.model.bean.ExchangeList;
import com.huohuo.mvp.model.bean.FengcaiList;

/**
 * Created by kennysun on 2019/8/7.
 */
public interface ExchangeListContract {
    interface View extends IView {
        void getExListSuccess(ExchangeList exchangeList);
    }

    interface Presenter extends IPresenter<View> {
        void getList(String token, String page, String size,String user_id);
    }
}