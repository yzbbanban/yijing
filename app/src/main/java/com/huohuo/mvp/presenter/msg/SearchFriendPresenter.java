package com.huohuo.mvp.presenter.msg;

import com.dian.commonlib.utils.SchedulerUtil;
import com.huohuo.base.HuoHuoBasePresenter;
import com.huohuo.net.HuoHuoErrorStatus;
import com.huohuo.dao.manager.DaoManager;
import com.huohuo.mvp.contract.msg.SearchFriendContract;

/**
 * Created by kennysun on 2019/9/4.
 */

public class SearchFriendPresenter extends HuoHuoBasePresenter<SearchFriendContract.View> implements SearchFriendContract.Presenter {

    @Override
    public void search(final String s) {
        DaoManager.getDaoManager().getFriendManager().search(s)
                .compose(SchedulerUtil.ioToMain())
                .subscribe(o -> getMvpView().search(o), throwable -> handException(throwable, HuoHuoErrorStatus.DAO_ERROR));
    }
}
