package com.yjb.mvp.presenter.home;

import com.dian.commonlib.net.RxHttpCallback;
import com.yjb.base.HuoHuoBasePresenter;
import com.yjb.mvp.contract.home.NewsViewIncContract;


/**
 * Created by kennysun on 2019/8/7.
 */

public class NVIcPresenter extends HuoHuoBasePresenter<NewsViewIncContract.View> implements NewsViewIncContract.Presenter {

    @Override
    public void getList(String token, String id) {
        if (token.isEmpty() || id.isEmpty()) {
            return;
        }
        doRequestToMain(dataManager.newsDetailIncr(token, id))
                .subscribeWith(new RxHttpCallback<Object>(this) {
                    @Override
                    public void onData(Object data) {
                        getMvpView().getNVIcSuccess(data);
                    }

                    @Override
                    public void onError(Object msg, int code) {
                        super.onError(msg, code);
                    }
                });
    }

}
