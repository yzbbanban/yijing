package com.yjb.mvp.presenter.msg;

import com.dian.commonlib.utils.SchedulerUtil;
import com.yjb.base.HuoHuoBasePresenter;
import com.yjb.net.HuoHuoErrorStatus;
import com.yjb.dao.manager.DaoManager;
import com.yjb.mvp.contract.msg.SearchFriendContract;

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
