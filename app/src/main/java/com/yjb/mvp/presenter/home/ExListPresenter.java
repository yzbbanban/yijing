package com.yjb.mvp.presenter.home;

import com.dian.commonlib.net.RxHttpCallback;
import com.yjb.base.HuoHuoBasePresenter;
import com.yjb.mvp.contract.home.ExchangeListContract;
import com.yjb.mvp.model.bean.ExchangeList;


/**
 * Created by kennysun on 2019/8/7.
 */

public class ExListPresenter extends HuoHuoBasePresenter<ExchangeListContract.View> implements ExchangeListContract.Presenter {

    @Override
    public void getList(String token, String page, String size, String user_id) {
        if (token.isEmpty() || page.isEmpty() || size.isEmpty() || user_id.isEmpty()) {
            return;
        }
        doRequestToMain(dataManager.getEx(token, page, size, user_id))
                .subscribeWith(new RxHttpCallback<ExchangeList>(this) {
                    @Override
                    public void onData(ExchangeList data) {
                        getMvpView().getExListSuccess(data);
                    }

                    @Override
                    public void onError(Object msg, int code) {
                        super.onError(msg, code);
                    }
                });
    }

}
