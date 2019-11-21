package com.yjb.mvp.contract.home;


import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;

/**
 * Created by kennysun on 2019/8/7.
 */
public interface YjApplyContract {
    interface View extends IView {
        void getApplySuccess(String o);
    }

    interface Presenter extends IPresenter<View> {
        void getList(String token,
                     String user_id,
                     String vision,
                     String photoimage,
                     String homeaddress,
                     String politically,
                     String identifier,
                     String job,
                     String nickname,
                     String gender,
                     String birthday);
    }
}
