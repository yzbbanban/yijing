package com.huohuo.mvp.contract.msg;

import com.dian.commonlib.base.IPresenter;
import com.dian.commonlib.base.IView;
import com.huohuo.dao.table.Group;

import java.util.List;

/**
 * Created by kennysun on 2019/9/4.
 */

public interface GroupContract {
    interface View extends IView {
        void groups(List<Group> groups);
    }

    interface Presenter extends IPresenter<View> {
        void getGroups();
    }
}
