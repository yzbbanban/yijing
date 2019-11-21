package com.yjb.mvp.contract.home;


import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;
import com.yjb.mvp.model.bean.MallList;

/**
 * Created by kennysun on 2019/8/7.
 */
public interface MallListContract {
    interface View extends IView {
        void getMallListSuccess(MallList mallList);
    }

    interface Presenter extends IPresenter<View> {
        void getList(String token, String page, String size, String user_id);
    }
}
