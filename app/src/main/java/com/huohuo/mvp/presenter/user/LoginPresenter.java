package com.huohuo.mvp.presenter.user;

import com.dian.commonlib.net.RxHttpCallback;
import com.dian.commonlib.utils.ToastUtil;
import com.huohuo.R;
import com.huohuo.base.HuoHuoBasePresenter;
import com.huohuo.net.HuoHuoErrorStatus;
import com.huohuo.mvp.contract.user.LoginContract;


/**
 * Created by kennysun on 2019/8/7.
 */

public class LoginPresenter extends HuoHuoBasePresenter<LoginContract.View> implements LoginContract.Presenter {

    @Override
    public void getCode(String account) {
        if (account.isEmpty()) {
            ToastUtil.show(mContext, R.string.account_not_empty);
            return;
        }
        doRequestToMain(dataManager.sendLoginSms(account, "login"))
                .subscribeWith(new RxHttpCallback<Object>(this) {
                    @Override
                    public void onData(Object data) {
                        getMvpView().getCodeSuccess();
                    }

                    @Override
                    public void onError(Object msg, int code) {
                        if (code == HuoHuoErrorStatus.USER_NOT_EXISTS_ERROR) {
                            getMvpView().registNever();
                        } else {
                            super.onError(msg, code);
                        }
                    }
                });
    }

}
