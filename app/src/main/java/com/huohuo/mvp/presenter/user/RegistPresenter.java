package com.huohuo.mvp.presenter.user;

import com.dian.commonlib.net.RxHttpCallback;
import com.dian.commonlib.utils.AppUtil;
import com.dian.commonlib.utils.SignUtil;
import com.dian.commonlib.utils.ToastUtil;
import com.huohuo.R;
import com.huohuo.base.HuoHuoBasePresenter;
import com.huohuo.mvp.contract.user.RegistContract;
import com.huohuo.mvp.model.bean.AboutBean;
import com.huohuo.mvp.model.bean.TokenBean;

/**
 * Created by kennysun on 2019/8/29.
 */

public class RegistPresenter extends HuoHuoBasePresenter<RegistContract.View> implements RegistContract.Presenter {
    @Override
    public void getAbout() {
        doRequestToMain(dataManager.about(), false)
                .subscribeWith(new RxHttpCallback<AboutBean>(this) {
                    @Override
                    public void onData(AboutBean data) {
                        getMvpView().getAbout(data);
                    }
                });
    }

    @Override
    public void getRegistCode(String phoneCode, String phone) {
        if (phoneCode.isEmpty()) {
            ToastUtil.show(mContext, R.string.phone_code_not_empty);
            return;
        }
        if (phone.isEmpty()) {
            ToastUtil.show(mContext, R.string.hint_phone);
            return;
        }
        doRequestToMain(dataManager.getRegistCode(phoneCode, phone))
                .subscribeWith(new RxHttpCallback<Object>(this) {
                    @Override
                    public void onData(Object data) {
                        getMvpView().getRegistCodeSuccess();
                    }

                });
    }

    @Override
    public void regist(String countryCode, String mobile, String valiRegisterCode) {
        if (countryCode.isEmpty()) {
            ToastUtil.show(mContext, R.string.phone_code_not_empty);
            return;
        }
        if (mobile.isEmpty()) {
            ToastUtil.show(mContext, R.string.hint_phone);
            return;
        }
        if (valiRegisterCode.isEmpty()) {
            ToastUtil.show(mContext, R.string.code_not_empty);
            return;
        }
        doRequestToMain(dataManager.regist(countryCode, mobile, valiRegisterCode))
                .subscribeWith(new RxHttpCallback<TokenBean>(this) {
                    @Override
                    public void onData(TokenBean data) {
                        AppUtil.setToken(data.getToken());
                        SignUtil.saveSecret(data.getSecret());
                        getMvpView().registSuccess();
                    }
                });
    }
}
