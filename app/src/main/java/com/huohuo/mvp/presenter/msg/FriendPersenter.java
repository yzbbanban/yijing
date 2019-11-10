package com.huohuo.mvp.presenter.msg;

import com.dian.commonlib.net.HttpResult;
import com.dian.commonlib.utils.LogUtil;
import com.dian.commonlib.utils.MmkvUtil;
import com.dian.commonlib.utils.SchedulerUtil;
import com.google.gson.Gson;
import com.huohuo.app.HuoHuoConstants;
import com.huohuo.base.HuoHuoBasePresenter;
import com.huohuo.net.HuoHuoErrorStatus;
import com.huohuo.dao.manager.DaoManager;
import com.huohuo.dao.table.Friend;
import com.huohuo.mvp.contract.msg.FriendContract;
import com.huohuo.mvp.model.bean.ContractsBean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by kennysun on 2019/9/3.
 */

public class FriendPersenter extends HuoHuoBasePresenter<FriendContract.View> implements FriendContract.Presenter {

    @Override
    public void loadFriend() {
        DaoManager.getDaoManager().getFriendManager().selectList()//获取数据库数据
                .flatMap(friends -> {
                    if (friends != null && friends.size() > 0) {
                        return Observable.just(friends);
                    } else {
                        return dataManager.getFriends();//获取网络数据
                    }
                })
                .map(o -> {
                    List<Friend> list = new ArrayList<>();
                    if (o instanceof HttpResult) {
                        HttpResult httpResult = (HttpResult) o;
                        int code = httpResult.getCode();
                        if (code == 200) {
                            ContractsBean contractsBean = (ContractsBean) httpResult.getData();
                            MmkvUtil.encodeBoolean(HuoHuoConstants.CONTRACT_NEW_APPLY, contractsBean.isNewApplyInfo());
                            List<Friend> personalFriendInfoVOS = contractsBean.getPersonalFriendInfoVOS();
                            list = DaoManager.getDaoManager().getFriendManager().handFriendList(personalFriendInfoVOS);
                            DaoManager.getDaoManager().getFriendManager().insertList(list);
                        } else {
                            handApiException(httpResult);
                        }
                    } else {
                        list = (List<Friend>) o;
                    }
                    return list;
                })
                .compose(SchedulerUtil.ioToMain())
                .subscribe(friends -> {
                    Collections.sort(friends);
                    LogUtil.d("friends=" + new Gson().toJson(friends));
                    List<String> customLetters = new ArrayList<>();
                    HashMap<String, Integer> letters = new HashMap<>();
                    int position = 0;
                    for (Friend friend : friends) {
                        String letter = friend.getFirstChar();
                        //如果没有这个key则加入并把位置也加入
                        if (!letters.containsKey(letter)) {
                            letters.put(letter, position);
                            customLetters.add(letter);
                        }
                        position++;
                    }
                    getMvpView().friend(friends, customLetters, letters);
                }, throwable -> {
                    handException(throwable, HuoHuoErrorStatus.DAO_ERROR);
                });
    }
}
