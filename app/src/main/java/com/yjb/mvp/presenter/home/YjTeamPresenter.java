package com.yjb.mvp.presenter.home;

import com.dian.commonlib.net.RxHttpCallback;
import com.yjb.base.HuoHuoBasePresenter;
import com.yjb.mvp.contract.home.YjAcListContract;
import com.yjb.mvp.contract.home.YjTeamContract;
import com.yjb.mvp.model.bean.ActivityList;
import com.yjb.mvp.model.bean.YjTeam;


/**
 * Created by kennysun on 2019/8/7.
 */

public class YjTeamPresenter extends HuoHuoBasePresenter<YjTeamContract.View> implements YjTeamContract.Presenter {

    @Override
    public void getList(String token, String page, String size) {
        if (token.isEmpty() || page.isEmpty() || size.isEmpty()) {
            return;
        }
        doRequestToMain(dataManager.yjTeam(token, page, size))
                .subscribeWith(new RxHttpCallback<YjTeam>(this) {
                    @Override
                    public void onData(YjTeam data) {
                        getMvpView().getYjTeamSuccess(data);
                    }

                    @Override
                    public void onError(Object msg, int code) {
                        super.onError(msg, code);
                    }
                });
    }

}
