package com.huohuo.mvp.presenter.home;

import com.dian.commonlib.net.RxHttpCallback;
import com.huohuo.base.HuoHuoBasePresenter;
import com.huohuo.mvp.contract.home.MyAcListContract;
import com.huohuo.mvp.contract.home.NewsListContract;
import com.huohuo.mvp.model.bean.AcMyList;
import com.huohuo.mvp.model.bean.NewsList;


/**
 * Created by kennysun on 2019/8/7.
 */

public class MyAcListPresenter extends HuoHuoBasePresenter<MyAcListContract.View> implements MyAcListContract.Presenter {

    @Override
    public void getList(String token, String page, String size, String user_id) {
        if (token.isEmpty() || page.isEmpty() || size.isEmpty() || user_id.isEmpty()) {
            return;
        }
        doRequestToMain(dataManager.getMyac(token, page, size, user_id))
                .subscribeWith(new RxHttpCallback<AcMyList>(this) {
                    @Override
                    public void onData(AcMyList data) {
                        getMvpView().getMyAcSuccess(data);
                    }

                    @Override
                    public void onError(Object msg, int code) {
                        super.onError(msg, code);
                    }
                });
    }

}
