package com.huohuo.mvp.contract.msg;

import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;
import com.huohuo.dao.table.FriendApply;

import java.util.List;

/**
 * Created by kennysun on 2019/9/3.
 */

public interface NewFriendContract {
    interface View extends IView {
        void friendApply(List<FriendApply> friends);

        void agreeOrRefuseSuccess();

    }

    interface Presenter extends IPresenter<View> {
        /**
         * 回复好友申请
         *
         * @param friendUid
         * @param replyType 好友申请回复类型 1:同意 2:删除消息
         * @return
         */
        void replyApply(String friendUid, int replyType);

        /**
         * 获取好友申请列表
         *
         * @return
         */
        void listFriendApply();
    }
}
