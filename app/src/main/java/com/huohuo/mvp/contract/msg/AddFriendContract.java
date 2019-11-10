package com.huohuo.mvp.contract.msg;

import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;

/**
 * Created by kennysun on 2019/9/4.
 */

public interface AddFriendContract {
    interface View extends IView {
        void getJiamiUserId(String s);
    }

    interface Presenter extends IPresenter<View> {
        void getJiamiUserId();
    }
}
