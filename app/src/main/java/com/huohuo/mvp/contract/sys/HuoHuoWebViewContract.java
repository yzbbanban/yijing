package com.huohuo.mvp.contract.sys;

import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;

/**
 * Created by kennysun on 2019/8/29.
 */

public interface HuoHuoWebViewContract {
    interface View extends IView {
        void checkOldPwdSuccess();

        void checkOldPwdError();

        void showPwdTipDialog(Object msg);

    }

    interface Presenter extends IPresenter<View> {
        void checkPayPwd(String oldPayPwd);
    }
}
