package com.yjb.mvp.presenter.home;

import com.dian.commonlib.net.RxHttpCallback;
import com.yjb.base.HuoHuoBasePresenter;
import com.yjb.mvp.contract.home.NewsListContract;
import com.yjb.mvp.model.bean.NewsList;


/**
 * Created by kennysun on 2019/8/7.
 */

public class NewsListPresenter extends HuoHuoBasePresenter<NewsListContract.View> implements NewsListContract.Presenter {

    @Override
    public void getList(String token, String page, String size) {
        if (token.isEmpty() || page.isEmpty() || size.isEmpty()) {
            return;
        }
        doRequestToMain(dataManager.getNews(token, page, size))
                .subscribeWith(new RxHttpCallback<NewsList>(this) {
                    @Override
                    public void onData(NewsList data) {
                        getMvpView().getNewsListSuccess(data);
                    }

                    @Override
                    public void onError(Object msg, int code) {
                        super.onError(msg, code);
                    }
                });
    }

}
