package com.yjb.mvp.presenter.sys;

import com.dian.commonlib.net.RxHttpCallback;
import com.yjb.base.HuoHuoBasePresenter;
import com.yjb.net.HuoHuoErrorStatus;
import com.yjb.mvp.contract.sys.HuoHuoWebViewContract;

/**
 * Created by kennysun on 2019/8/29.
 */

public class HuoHuoWebViewPresenter extends HuoHuoBasePresenter<HuoHuoWebViewContract.View> implements HuoHuoWebViewContract.Presenter {
    @Override
    public void checkPayPwd(String oldPayPwd) {
        doRequestToMain(dataManager.checkPayPwd(oldPayPwd))
                .subscribeWith(new RxHttpCallback<Object>(this) {
                    @Override
                    public void onData(Object data) {
                        getMvpView().checkOldPwdSuccess();
                    }

                    @Override
                    public void onError(Object msg, int code) {
                        if (code == HuoHuoErrorStatus.USER_PAY_PWD_FORZEN) {
                            //支付密码被冻结
                            getMvpView().showPwdTipDialog(msg);
                        } else {
                            getMvpView().checkOldPwdError();
                            super.onError(msg, code);
                        }
                    }
                });
    }
}
