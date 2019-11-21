package com.yjb.mvp.contract.home;


import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;
import com.yjb.mvp.model.bean.YjTeam;

/**
 * Created by kennysun on 2019/8/7.
 */
public interface YjTeamContract {
    interface View extends IView {
        void getYjTeamSuccess(YjTeam data);
    }

    interface Presenter extends IPresenter<View> {
        void getList(String token, String page, String size);
    }
}
