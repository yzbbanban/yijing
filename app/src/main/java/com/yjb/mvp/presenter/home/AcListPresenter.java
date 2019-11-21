package com.yjb.mvp.presenter.home;

import com.dian.commonlib.net.RxHttpCallback;
import com.yjb.base.HuoHuoBasePresenter;
import com.yjb.mvp.contract.home.YjAcListContract;
import com.yjb.mvp.model.bean.ActivityList;


/**
 * Created by kennysun on 2019/8/7.
 */

public class AcListPresenter extends HuoHuoBasePresenter<YjAcListContract.View> implements YjAcListContract.Presenter {

    @Override
    public void getList(String token, String page, String size, String type) {
        if (token.isEmpty() || page.isEmpty() || size.isEmpty() || type.isEmpty()) {
            return;
        }
        doRequestToMain(dataManager.getYjAc(token, page, size, type))
                .subscribeWith(new RxHttpCallback<ActivityList>(this) {
                    @Override
                    public void onData(ActivityList data) {
                        getMvpView().getAcYjListSuccess(data);
                    }

                    @Override
                    public void onError(Object msg, int code) {
                        super.onError(msg, code);
                    }
                });
    }

}
