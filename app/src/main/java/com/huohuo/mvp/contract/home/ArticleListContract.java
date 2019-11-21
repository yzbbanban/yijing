package com.huohuo.mvp.contract.home;


import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;
import com.huohuo.mvp.model.bean.ArticleList;

/**
 * Created by kennysun on 2019/8/7.
 */
public interface ArticleListContract {
    interface View extends IView {
        void getAtListSuccess(ArticleList articleList);
    }

    interface Presenter extends IPresenter<View> {
        void getList(String token, String page, String size);
    }
}
