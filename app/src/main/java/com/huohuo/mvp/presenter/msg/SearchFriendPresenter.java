package com.huohuo.mvp.presenter.msg;

import com.dian.commonlib.net.RxHttpCallback;
import com.dian.commonlib.utils.SchedulerUtil;
import com.huohuo.base.HuoHuoBasePresenter;
import com.huohuo.dao.manager.DaoManager;
import com.huohuo.dao.table.Friend;
import com.huohuo.dao.table.FriendApply;
import com.huohuo.mvp.contract.msg.NewFriendContract;
import com.huohuo.mvp.contract.msg.NewFriendContract.Presenter;
import com.huohuo.mvp.contract.msg.SearchFriendContract;
import com.huohuo.net.HuoHuoErrorStatus;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

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
