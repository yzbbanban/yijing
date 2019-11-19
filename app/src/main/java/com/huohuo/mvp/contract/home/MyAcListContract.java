package com.huohuo.mvp.contract.home;


import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;
import com.huohuo.mvp.model.bean.AcMyList;
import com.huohuo.mvp.model.bean.NewsList;

/**
 * Created by kennysun on 2019/8/7.
 */

public interface MyAcListContract {
    interface View extends IView {
        void getMyAcSuccess(AcMyList acMyList);
    }

    interface Presenter extends IPresenter<View> {
        void getList(String token, String page, String size, String user_id);
    }
}
