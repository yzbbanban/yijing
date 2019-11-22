package com.yjb.mvp.presenter.user;

import com.dian.commonlib.net.RxHttpCallback;
import com.dian.commonlib.utils.AppUtil;
import com.dian.commonlib.utils.ToastUtil;
import com.yjb.R;
import com.yjb.base.HuoHuoBasePresenter;
import com.yjb.mvp.model.bean.UserInfo;
import com.yjb.mvp.contract.user.InputLoginCodeContract;
import com.yjb.mvp.model.bean.JiYanData;

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
        doRequestToMain(dataManager.userLogin(account, code))
                .subscribeWith(new RxHttpCallback<UserInfo>(this) {
                    @Override
                    public void onData(UserInfo data) {
                        AppUtil.setToken(data.getUserinfo().getToken());
                        AppUtil.setUser("" + data.getUserinfo().getUser_id());
                        AppUtil.setImage(data.getUserinfo().getAvatar());
                        AppUtil.setNickName(data.getUserinfo().getNickname());
                        if (data.getYjinfo() == null) {
                            AppUtil.setTeamId("");
                        } else {
                            AppUtil.setTeamId("" + data.getYjinfo().getTeammgt_id());
                        }
                        getMvpView().loginSuccess();
                    }

                    @Override
                    public void onError(Object msg, int code) {
                        super.onError(msg, code);
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
