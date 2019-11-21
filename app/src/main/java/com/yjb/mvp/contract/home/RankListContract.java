package com.yjb.mvp.contract.home;


import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;
import com.yjb.mvp.model.bean.RankList;

/**
 * Created by kennysun on 2019/8/7.
 */
public interface RankListContract {
    interface View extends IView {
        void getRankListSuccess(RankList msg);
    }

    interface Presenter extends IPresenter<View> {
        void getList(String token, String page, String size, int type);
    }
}
