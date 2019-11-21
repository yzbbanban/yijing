package com.yjb.mvp.contract.home;


import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;
import com.yjb.mvp.model.bean.ActivityList;

/**
 * Created by kennysun on 2019/8/7.
 */

public interface YjAcListContract {
    interface View extends IView {
        void getAcYjListSuccess(ActivityList activityList);
    }

    interface Presenter extends IPresenter<View> {
        void getList(String token, String page, String size, String type);
    }
}
