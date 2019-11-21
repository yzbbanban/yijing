package com.yjb.mvp.presenter.home;

import com.dian.commonlib.net.RxHttpCallback;
import com.yjb.base.HuoHuoBasePresenter;
import com.yjb.mvp.contract.home.YjApplyContract;


/**
 * Created by kennysun on 2019/8/7.
 */

public class YjApplyPresenter extends HuoHuoBasePresenter<YjApplyContract.View> implements YjApplyContract.Presenter {

    @Override
    public void getList(String token,
                        String user_id,
                        String vision,
                        String photoimage,
                        String homeaddress,
                        String politically,
                        String identifier,
                        String job,
                        String nickname,
                        String gender,
                        String birthday) {
        if (token.isEmpty() || user_id.isEmpty() || identifier.isEmpty()) {
            return;
        }
        doRequestToMain(dataManager.yjApply(token, user_id, vision, photoimage, homeaddress,
                politically, identifier, job, nickname, gender, birthday))
                .subscribeWith(new RxHttpCallback<String>(this) {
                    @Override
                    public void onData(String data) {
                        getMvpView().getApplySuccess(data);
                    }

                    @Override
                    public void onError(Object msg, int code) {
                        super.onError(msg, code);
                    }
                });
    }

}