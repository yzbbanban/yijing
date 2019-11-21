package com.yjb.mvp.presenter.home;

import com.dian.commonlib.net.RxHttpCallback;
import com.yjb.base.HuoHuoBasePresenter;
import com.yjb.mvp.contract.home.HomeYjRankContract;
import com.yjb.mvp.model.bean.HomeYjRank;

import java.util.List;

/**
 * Created by kennysun on 2019/8/7.
 */

public class HomeYjRankPresenter extends HuoHuoBasePresenter<HomeYjRankContract.View> implements HomeYjRankContract.Presenter {

    @Override
    public void getList(String token) {
        if (token.isEmpty()) {
            return;
        }
        doRequestToMain(dataManager.homeYjRank(token))
                .subscribeWith(new RxHttpCallback<List<HomeYjRank>>(this) {
                    @Override
                    public void onData(List<HomeYjRank> data) {
                        getMvpView().getRankSuccess(data);
                    }

                    @Override
                    public void onError(Object msg, int code) {
                        super.onError(msg, code);
                    }
                });
    }

}
