package com.huohuo.mvp.contract.msg;

import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;
import com.huohuo.dao.table.Friend;

import java.util.HashMap;
import java.util.List;

/**
 * Created by kennysun on 2019/9/3.
 */

public interface FriendContract {
    interface View extends IView {
        void friend(List<Friend> friends, List<String> customLetters, HashMap<String, Integer> letters);
    }

    interface Presenter extends IPresenter<View> {
        void loadFriend();
    }
}
