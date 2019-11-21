package com.yjb.mvp.presenter.home;

import com.dian.commonlib.net.RxHttpCallback;
import com.yjb.base.HuoHuoBasePresenter;
import com.yjb.mvp.contract.home.AcSignUpContract;


/**
 * Created by kennysun on 2019/8/7.
 */

public class AcSIgnUpPresenter extends HuoHuoBasePresenter<AcSignUpContract.View> implements AcSignUpContract.Presenter {

    @Override
    public void getList(String token,
                        String activity_id,
                        String user_id,
                        String teammgt_id) {
        if (token.isEmpty() || activity_id.isEmpty() || user_id.isEmpty() || teammgt_id.isEmpty()) {
            return;
        }
        doRequestToMain(dataManager.activitySignUp(token, activity_id, user_id, teammgt_id))
                .subscribeWith(new RxHttpCallback<String>(this) {
                    @Override
                    public void onData(String data) {
                        getMvpView().getAcSignUpSuccess(data);
                    }

                    @Override
                    public void onError(Object msg, int code) {
                        super.onError(msg, code);
                    }
                });
    }

}
