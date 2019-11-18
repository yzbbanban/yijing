package com.huohuo.mvp.presenter.user;

import com.dian.commonlib.net.RxHttpCallback;
import com.dian.commonlib.utils.AppUtil;
import com.dian.commonlib.utils.ToastUtil;
import com.huohuo.R;
import com.huohuo.base.HuoHuoBasePresenter;
import com.huohuo.mvp.model.bean.UserInfo;
import com.huohuo.net.HuoHuoErrorStatus;
import com.huohuo.mvp.contract.user.InputLoginCodeContract;
import com.huohuo.mvp.model.bean.JiYanData;

import okhttp3.RequestBody;

/**
 * Created by kennysun on 2019/8/28.
 */

public class InputLoginCodePresenter extends HuoHuoBasePresenter<InputLoginCodeContract.View> implements InputLoginCodeContract.Presenter {

    @Override
    public void login(String phoneCode, String account, String code) {
        if (phoneCode.isEmpty()) {
            ToastUtil.show(mContext, R.string.phone_code_not_empty);
            return;
        }
        if (account.isEmpty()) {
            ToastUtil.show(mContext, R.string.account_not_empty);
            return;
        }
        if (code.isEmpty()) {
            ToastUtil.show(mContext, R.string.code_not_empty);
            return;
        }
        doRequestToMain(dataManager.userLogin(phoneCode, account, code))
                .subscribeWith(new RxHttpCallback<UserInfo>(this) {
                    @Override
                    public void onData(UserInfo data) {
                        AppUtil.setToken(data.getUserinfo().getToken());
                        getMvpView().loginSuccess();
                    }

                    @Override
                    public void onError(Object msg, int code) {
                        if (code == HuoHuoErrorStatus.PERSONAL_USER_ACCOUNT_LOGIN_GEETEST_ERROR) {//登录失败，启用滑动验证
                            //启用极验验证
                            getMvpView().geeTest();
                        } else if (code == HuoHuoErrorStatus.LOGIN_SUCCESS_IN_OTHER_DEVICE) {//登录成功，用户在另一个设备进行登录

                        } else if (code == HuoHuoErrorStatus.USER_NOT_EXISTS_ERROR) {//手机号未注册
                        } else if (code == HuoHuoErrorStatus.SMS_CODE_ERROR) {           //验证码错误
                            getMvpView().codeError();
                        } else {
                            super.onError(msg, code);
                        }
                    }
                });
    }

    @Override
    public void jiYanApi1() {
        doRequestToMain(dataManager.jiYanApi1())
                .subscribeWith(new RxHttpCallback<JiYanData>(this) {
                    @Override
                    public void onData(JiYanData data) {
                        getMvpView().jiYanApi1(data);
                    }
                });
    }

    @Override
    public void jiYanApi2(String challenge, String validate, String userName, String countryCode, RequestBody seccode) {
        doRequestToMain(dataManager.jiYanApi2(challenge, validate, userName, countryCode, seccode))
                .subscribeWith(new RxHttpCallback<Object>(this) {
                    @Override
                    public void onData(Object data) {
                        getMvpView().jiYanApi2();
                    }
                });
    }

}
