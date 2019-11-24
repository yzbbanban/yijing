package com.yjb.mvp.presenter.home;

import com.dian.commonlib.net.RxHttpCallback;
import com.yjb.base.HuoHuoBasePresenter;
import com.yjb.mvp.contract.home.ExchangePayContract;
import com.yjb.mvp.contract.home.GetSignStautsContract;


/**
 * Created by kennysun on 2019/8/7.
 */

public class ExchangePayPresenter extends HuoHuoBasePresenter<ExchangePayContract.View> implements ExchangePayContract.Presenter {

    @Override
    public void getList(String token,
                        String user_id,
                        String id,
                        String score) {
        if (token.isEmpty() || id.isEmpty() || user_id.isEmpty()) {
            return;
        }
        doRequestToMain(dataManager.exchangeAPay(token, user_id, id, score))
                .subscribeWith(new RxHttpCallback<String>(this) {
                    @Override
                    public void onData(String data) {
                        getMvpView().getPaySuccess(data);
                    }

                    @Override
                    public void onError(Object msg, int code) {
                        super.onError(msg, code);
                    }
                });
    }

}
