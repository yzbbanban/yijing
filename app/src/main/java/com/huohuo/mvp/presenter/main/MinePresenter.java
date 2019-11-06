package com.huohuo.mvp.presenter.main;

import com.dian.commonlib.net.RxHttpCallback;
import com.huohuo.base.HuoHuoBasePresenter;
import com.huohuo.mvp.contract.main.MineContract;


/**
 * Created by kennysun on 2019/8/7.
 */

public class MinePresenter extends HuoHuoBasePresenter<MineContract.View> implements MineContract.Presenter {

    @Override
    public void loginOut() {
        doRequestToMain(dataManager.userExit())
                .subscribeWith(new RxHttpCallback<Object>(this) {
                    @Override
                    public void onData(Object data) {
                        getMvpView().loginOut();
                    }
                });
    }
}
