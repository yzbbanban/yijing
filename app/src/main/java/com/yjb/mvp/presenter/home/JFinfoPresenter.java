package com.yjb.mvp.presenter.home;

import com.dian.commonlib.net.RxHttpCallback;
import com.yjb.base.HuoHuoBasePresenter;
import com.yjb.mvp.contract.home.JFinfoContract;
import com.yjb.mvp.contract.home.YjAcListContract;
import com.yjb.mvp.model.bean.ActivityList;
import com.yjb.mvp.model.bean.JFInfo;


/**
 * Created by kennysun on 2019/8/7.
 */

public class JFinfoPresenter extends HuoHuoBasePresenter<JFinfoContract.View> implements JFinfoContract.Presenter {

    @Override
    public void getList(String token, String user_id) {
        if (token.isEmpty() || user_id.isEmpty()) {
            return;
        }
        doRequestToMain(dataManager.jFinfo(token, user_id))
                .subscribeWith(new RxHttpCallback<JFInfo>(this) {
                    @Override
                    public void onData(JFInfo data) {
                        getMvpView().getJFInfoSuccess(data);
                    }

                    @Override
                    public void onError(Object msg, int code) {
                        super.onError(msg, code);
                    }
                });
    }

}
