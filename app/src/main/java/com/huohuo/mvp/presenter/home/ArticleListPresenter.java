package com.huohuo.mvp.presenter.home;

import com.dian.commonlib.net.RxHttpCallback;
import com.huohuo.base.HuoHuoBasePresenter;
import com.huohuo.mvp.contract.home.ArticleListContract;
import com.huohuo.mvp.contract.home.NewsListContract;
import com.huohuo.mvp.model.bean.ArticleList;
import com.huohuo.mvp.model.bean.NewsList;


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
