package com.yjb.mvp.contract.msg;

import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;
import com.yjb.dao.table.Friend;
import com.yjb.mvp.model.bean.PhoneInfoBean;

import java.util.HashMap;
import java.util.List;

/**
 * Created by kennysun on 2019/9/3.
 */

public interface PhoneFriendContract {
    interface View extends IView {
        void phoneFriend(List<Friend> list,List<String> customLetters,HashMap<String, Integer> letters );
        void smsContent(String s);
        void addFriendSendSuccess();
    }

    interface Presenter extends IPresenter<View> {
        List<PhoneInfoBean> loadPhoneContract();
        void loadPhoneFriend();
        void getSmsContent();
        void addFriend(String fId);
    }
}
