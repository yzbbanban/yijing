package com.huohuo.mvp.presenter.home;

import com.dian.commonlib.net.RxHttpCallback;
import com.huohuo.base.HuoHuoBasePresenter;
import com.huohuo.mvp.contract.home.AcSignOutContract;
import com.huohuo.mvp.contract.home.AcSignUpContract;


/**
 * Created by kennysun on 2019/8/7.
 */

public class AcSIgnOutPresenter extends HuoHuoBasePresenter<AcSignOutContract.View> implements AcSignOutContract.Presenter {

    @Override
    public void getList(String token, String fakeid, String activity_id) {
        if (token.isEmpty() || activity_id.isEmpty() || fakeid.isEmpty()) {
            return;
        }
        doRequestToMain(dataManager.activitySignOut(token, fakeid, activity_id))
                .subscribeWith(new RxHttpCallback<String>(this) {
                    @Override
                    public void onData(String data) {
                        getMvpView().getAcSignOutSuccess(data);
                    }

                    @Override
                    public void onError(Object msg, int code) {
                        super.onError(msg, code);
                    }
                });
    }

}
