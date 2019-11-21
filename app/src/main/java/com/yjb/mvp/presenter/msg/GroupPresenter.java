package com.yjb.mvp.presenter.msg;

import com.dian.commonlib.net.HttpResult;
import com.dian.commonlib.utils.SchedulerUtil;
import com.yjb.base.HuoHuoBasePresenter;
import com.yjb.net.HuoHuoErrorStatus;
import com.yjb.dao.manager.DaoManager;
import com.yjb.dao.table.Group;
import com.yjb.mvp.contract.msg.GroupContract;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

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
