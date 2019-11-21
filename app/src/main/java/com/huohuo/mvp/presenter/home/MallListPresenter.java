package com.huohuo.mvp.presenter.home;

import com.dian.commonlib.net.RxHttpCallback;
import com.huohuo.base.HuoHuoBasePresenter;
import com.huohuo.mvp.contract.home.MallListContract;
import com.huohuo.mvp.contract.home.YjFcListContract;
import com.huohuo.mvp.model.bean.FengcaiList;
import com.huohuo.mvp.model.bean.MallList;


/**
 * Created by kennysun on 2019/8/7.
 */

public class MallListPresenter extends HuoHuoBasePresenter<MallListContract.View> implements MallListContract.Presenter {

    @Override
    public void getList(String token, String page, String size, String user_id) {
        if (token.isEmpty() || page.isEmpty() || size.isEmpty() || user_id.isEmpty()) {
            return;
        }
        doRequestToMain(dataManager.getMall(token, page, size, user_id))
                .subscribeWith(new RxHttpCallback<MallList>(this) {
                    @Override
                    public void onData(MallList data) {
                        getMvpView().getMallListSuccess(data);
                    }

                    @Override
                    public void onError(Object msg, int code) {
                        super.onError(msg, code);
                    }
                });
    }

}