package com.yjb.mvp.presenter.home;

import com.dian.commonlib.net.RxHttpCallback;
import com.yjb.base.HuoHuoBasePresenter;
import com.yjb.mvp.contract.home.CommonUploadContract;

import okhttp3.RequestBody;


/**
 * Created by kennysun on 2019/8/7.
 */

public class CommonUploadPresenter extends HuoHuoBasePresenter<CommonUploadContract.View> implements CommonUploadContract.Presenter {

    @Override
    public void getList(String token,
                        RequestBody file) {
        if (token.isEmpty() || file == null) {
            return;
        }
        doRequestToMain(dataManager.upload(token, file))
                .subscribeWith(new RxHttpCallback<String>(this) {
                    @Override
                    public void onData(String data) {
                        getMvpView().getUpload(data);
                    }

                    @Override
                    public void onError(Object msg, int code) {
                        super.onError(msg, code);
                    }
                });
    }

}
