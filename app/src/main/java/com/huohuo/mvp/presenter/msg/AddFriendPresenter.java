package com.huohuo.mvp.presenter.msg;

import com.dian.commonlib.net.RxHttpCallback;
import com.huohuo.base.HuoHuoBasePresenter;
import com.huohuo.mvp.contract.msg.AddFriendContract;

/**
 * Created by kennysun on 2019/9/4.
 */

public class AddFriendPresenter extends HuoHuoBasePresenter<AddFriendContract.View> implements AddFriendContract.Presenter {
    @Override
    public void getJiamiUserId() {
        doRequestToMain(dataManager.getJiamiUserId())
                .subscribeWith(new RxHttpCallback<String>(this) {
                    @Override
                    public void onData(String data) {
                        getMvpView().getJiamiUserId(data);
                    }
                });
    }
}
