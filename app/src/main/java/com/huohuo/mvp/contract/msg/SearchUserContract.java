package com.huohuo.mvp.contract.msg;

import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;
import com.huohuo.dao.table.Friend;

/**
 * Created by kennysun on 2019/9/3.
 */

public interface SearchUserContract {
    interface View extends IView {

        void search(Friend list);

    }

    interface Presenter extends IPresenter<View> {

        void search(String s);
    }
}
