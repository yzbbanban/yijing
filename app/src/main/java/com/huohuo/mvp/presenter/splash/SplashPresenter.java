package com.huohuo.mvp.presenter.splash;

import com.dian.commonlib.net.RxHttpCallback;
import com.dian.commonlib.utils.DateFormatUtil;
import com.huohuo.base.HuoHuoBasePresenter;
import com.huohuo.mvp.contract.splash.SplashContract;
import com.huohuo.mvp.model.bean.SplashBean;


/**
 * Created by kennysun on 2019/8/7.
 */

public class SplashPresenter extends HuoHuoBasePresenter<SplashContract.View> implements SplashContract.Presenter{

    @Override
    public void splash() {
        doRequestToMain(dataManager.getServiceTime())
                .subscribeWith(new RxHttpCallback<Long>(this) {
                    @Override
                    public void onData(Long data) {
                        DateFormatUtil.saveServiceTime(data);
                    }
                });
        doRequestToMain(dataManager.splash())
                .subscribeWith(new RxHttpCallback<SplashBean>(this) {
                    @Override
                    public void onData(SplashBean data) {
                        getMvpView().splash(data);
                    }
                });
    }
}
