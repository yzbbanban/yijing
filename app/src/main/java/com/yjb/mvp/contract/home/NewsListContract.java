package com.yjb.mvp.contract.home;


import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;
import com.yjb.mvp.model.bean.NewsList;

/**
 * Created by kennysun on 2019/8/7.
 */

public interface NewsListContract {
    interface View extends IView {
        void getNewsListSuccess(NewsList newsList);
    }

    interface Presenter extends IPresenter<View> {
        void getList(String token, String page, String size);
    }
}
