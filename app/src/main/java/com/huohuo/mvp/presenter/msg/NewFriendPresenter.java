package com.huohuo.mvp.presenter.msg;

import com.dian.commonlib.net.RxHttpCallback;
import com.huohuo.base.HuoHuoBasePresenter;
import com.huohuo.dao.table.FriendApply;
import com.huohuo.mvp.contract.msg.NewFriendContract;

import java.util.List;

/**
 * Created by kennysun on 2019/9/4.
 */

public class NewFriendPresenter extends HuoHuoBasePresenter<NewFriendContract.View> implements NewFriendContract.Presenter {
    @Override
    public void replyApply(String friendUid, int replyType) {
        doRequestToMain(dataManager.replyApply(friendUid, replyType))
                .subscribeWith(new RxHttpCallback<Object>(this) {
                    @Override
                    public void onData(Object data) {
                        getMvpView().agreeOrRefuseSuccess();
                    }
                });
    }

    @Override
    public void listFriendApply() {
        doRequestToMain(dataManager.listFriendApply())
                .subscribeWith(new RxHttpCallback<List<FriendApply>>(this) {
                    @Override
                    public void onData(List<FriendApply> data) {
                        getMvpView().friendApply(data);
                    }
                });
    }
}
