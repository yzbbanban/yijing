package com.yjb.mvp.presenter.home;

import com.dian.commonlib.net.RxHttpCallback;
import com.yjb.base.HuoHuoBasePresenter;
import com.yjb.mvp.contract.home.RankListContract;
import com.yjb.mvp.model.bean.RankList;


/**
 * Created by kennysun on 2019/8/7.
 */

public class RankListPresenter extends HuoHuoBasePresenter<RankListContract.View> implements RankListContract.Presenter {

    @Override
    public void getList(String token, String page, String size, int type) {
        if (token.isEmpty() || page.isEmpty() || size.isEmpty()) {
            return;
        }
        switch (type) {
            case 1:
                doRequestToMain(dataManager.rankMonthList(token, page, size))
                        .subscribeWith(new RxHttpCallback<RankList>(this) {
                            @Override
                            public void onData(RankList data) {
                                getMvpView().getRankListSuccess(data);
                            }

                            @Override
                            public void onError(Object msg, int code) {
                                super.onError(msg, code);
                            }
                        });
                break;
            case 2:
                doRequestToMain(dataManager.rankYearList(token, page, size))
                        .subscribeWith(new RxHttpCallback<RankList>(this) {
                            @Override
                            public void onData(RankList data) {
                                getMvpView().getRankListSuccess(data);
                            }

                            @Override
                            public void onError(Object msg, int code) {
                                super.onError(msg, code);
                            }
                        });
                break;
        }

    }

}
