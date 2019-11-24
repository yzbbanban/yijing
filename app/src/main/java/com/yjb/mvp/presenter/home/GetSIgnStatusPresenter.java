package com.yjb.mvp.presenter.home;

import com.dian.commonlib.net.RxHttpCallback;
import com.yjb.base.HuoHuoBasePresenter;
import com.yjb.mvp.contract.home.AcSignOutContract;
import com.yjb.mvp.contract.home.GetSignStautsContract;


/**
 * Created by kennysun on 2019/8/7.
 */

public class GetSIgnStatusPresenter extends HuoHuoBasePresenter<GetSignStautsContract.View> implements GetSignStautsContract.Presenter {

    @Override
    public void getList(String token, String activity_id, String user_id) {
        if (token.isEmpty() || activity_id.isEmpty() || user_id.isEmpty()) {
            return;
        }
        doRequestToMain(dataManager.getSignStatuInfo(token, activity_id, user_id))
                .subscribeWith(new RxHttpCallback<String>(this) {
                    @Override
                    public void onData(String data) {
                        getMvpView().getSignStatusSuccess(data);
                    }

                    @Override
                    public void onError(Object msg, int code) {
                        super.onError(msg, code);
                    }
                });
    }

}
