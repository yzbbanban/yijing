package com.huohuo.mvp.presenter.msg;

import com.dian.commonlib.net.HttpResult;
import com.dian.commonlib.utils.LogUtil;
import com.dian.commonlib.utils.MmkvUtil;
import com.dian.commonlib.utils.SchedulerUtil;
import com.google.gson.Gson;
import com.huohuo.app.HuoHuoConstants;
import com.huohuo.base.HuoHuoBasePresenter;
import com.huohuo.dao.manager.DaoManager;
import com.huohuo.dao.table.Friend;
import com.huohuo.dao.table.Group;
import com.huohuo.mvp.contract.msg.GroupContract;
import com.huohuo.mvp.model.bean.ContractsBean;
import com.huohuo.net.HuoHuoErrorStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;

/**
 * Created by kennysun on 2019/9/5.
 */

public class GroupPresenter extends HuoHuoBasePresenter<GroupContract.View> implements GroupContract.Presenter {
    @Override
    public void getGroups() {
        DaoManager.getDaoManager().getGroupManager().selectList()//获取数据库数据
                .flatMap(friends -> {
                    if (friends != null && friends.size() > 0) {
                        return Observable.just(friends);
                    } else {
                        return dataManager.getGroups();//获取网络数据
                    }
                })
                .map(o -> {
                    List<Group> groups = new ArrayList<>();
                    if (o instanceof HttpResult) {
                        HttpResult httpResult = (HttpResult) o;
                        int code = httpResult.getCode();
                        if (code == 200) {
                            groups = (List<Group>) httpResult.getData();
                            DaoManager.getDaoManager().getGroupManager().insertList(groups);
                        } else {
                            handApiException(httpResult);
                        }
                    } else {
                        groups = (List<Group>) o;
                    }
                    return groups;
                })
                .compose(SchedulerUtil.ioToMain())
                .subscribe(friends -> {
                    getMvpView().groups(friends);
                }, throwable -> {
                    handException(throwable, HuoHuoErrorStatus.DAO_ERROR);
                });
    }
}
