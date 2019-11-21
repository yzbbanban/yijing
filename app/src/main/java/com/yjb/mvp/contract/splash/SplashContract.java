package com.yjb.mvp.contract.splash;

import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;
import com.yjb.mvp.model.bean.SplashBean;

/**
 * Created by kennysun on 2019/8/7.
 */

public interface SplashContract {
    interface View extends IView {
        void splash(SplashBean data);
        void next();
    }

    interface Presenter extends IPresenter<View> {
        void splash();
    }
}
