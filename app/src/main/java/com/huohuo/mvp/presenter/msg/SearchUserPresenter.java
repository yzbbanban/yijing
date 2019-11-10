package com.huohuo.mvp.presenter.msg;

import com.dian.commonlib.net.RxHttpCallback;
import com.huohuo.base.HuoHuoBasePresenter;
import com.huohuo.dao.table.Friend;
import com.huohuo.mvp.contract.msg.SearchUserContract;

/**
 * Created by kennysun on 2019/9/4.
 */

public class SearchUserPresenter extends HuoHuoBasePresenter<SearchUserContract.View> implements SearchUserContract.Presenter {

    @Override
    public void search(final String s) {
        doRequestToMain(dataManager.getSearchFriendInfo("",s))
                .subscribeWith(new RxHttpCallback<Friend>(this) {
                    @Override
                    public void onData(Friend data) {
                        getMvpView().search(data);
                    }
                });
    }
}
