package com.yjb.mvp.contract.user;

import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;
import com.yjb.mvp.model.bean.AboutBean;

/**
 * Created by kennysun on 2019/8/29.
 */

public interface RegistContract {
    interface View extends IView {
        void getAbout(AboutBean aboutData);

        void getRegistCodeSuccess();

        void registSuccess();
    }

    interface Presenter extends IPresenter<View> {
        void getAbout();

        void getRegistCode(String phoneCode, String phone);

        void regist(String countryCode, String mobile, String valiRegisterCode);
    }
}
