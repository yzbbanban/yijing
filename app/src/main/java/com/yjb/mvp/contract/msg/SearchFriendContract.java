package com.yjb.mvp.contract.msg;

import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;
import com.yjb.dao.table.Friend;

import java.util.List;

/**
 * Created by kennysun on 2019/9/3.
 */

public interface SearchFriendContract {
    interface View extends IView {

        void search(List<Friend> list);

    }

    interface Presenter extends IPresenter<View> {

        void search(String s);
    }
}
