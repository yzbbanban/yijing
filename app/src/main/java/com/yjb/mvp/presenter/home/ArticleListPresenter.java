package com.yjb.mvp.presenter.home;

import com.dian.commonlib.net.RxHttpCallback;
import com.yjb.base.HuoHuoBasePresenter;
import com.yjb.mvp.contract.home.ArticleListContract;
import com.yjb.mvp.model.bean.ArticleList;


/**
 * Created by kennysun on 2019/8/7.
 */

public class ArticleListPresenter extends HuoHuoBasePresenter<ArticleListContract.View> implements ArticleListContract.Presenter {

    @Override
    public void getList(String token, String page, String size) {
        if (token.isEmpty() || page.isEmpty() || size.isEmpty()) {
            return;
        }
        doRequestToMain(dataManager.articleLlist(token, page, size))
                .subscribeWith(new RxHttpCallback<ArticleList>(this) {
                    @Override
                    public void onData(ArticleList data) {
                        getMvpView().getAtListSuccess(data);
                    }

                    @Override
                    public void onError(Object msg, int code) {
                        super.onError(msg, code);
                    }
                });
    }

}
