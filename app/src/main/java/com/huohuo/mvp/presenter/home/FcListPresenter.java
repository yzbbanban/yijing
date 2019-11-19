package com.huohuo.mvp.presenter.home;

import com.dian.commonlib.net.RxHttpCallback;
import com.huohuo.base.HuoHuoBasePresenter;
import com.huohuo.mvp.contract.home.YjAcListContract;
import com.huohuo.mvp.contract.home.YjFcListContract;
import com.huohuo.mvp.model.bean.ActivityList;
import com.huohuo.mvp.model.bean.FengcaiList;


/**
 * Created by kennysun on 2019/8/7.
 */

public class FcListPresenter extends HuoHuoBasePresenter<YjFcListContract.View> implements YjFcListContract.Presenter {

    @Override
    public void getList(String token, String page, String size) {
        if (token.isEmpty() || page.isEmpty() || size.isEmpty()) {
            return;
        }
        doRequestToMain(dataManager.getYjFc(token, page, size))
                .subscribeWith(new RxHttpCallback<FengcaiList>(this) {
                    @Override
                    public void onData(FengcaiList data) {
                        getMvpView().getYjFcListSuccess(data);
                    }

                    @Override
                    public void onError(Object msg, int code) {
                        super.onError(msg, code);
                    }
                });
    }

}
