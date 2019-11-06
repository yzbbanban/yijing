package com.huohuo.mvp.presenter.main;

import com.dian.commonlib.app.Constants;
import com.dian.commonlib.net.HttpResult;
import com.dian.commonlib.net.exception.ApiException;
import com.dian.commonlib.net.exception.ErrorStatus;
import com.dian.commonlib.net.exception.ExceptionHandle;
import com.dian.commonlib.utils.LogUtil;
import com.dian.commonlib.utils.MmkvUtil;
import com.dian.commonlib.utils.SchedulerUtil;
import com.huohuo.app.HuoHuoApp;
import com.huohuo.app.HuoHuoConstants;
import com.huohuo.base.HuoHuoBasePresenter;
import com.huohuo.dao.manager.DaoManager;
import com.huohuo.dao.manager.FriendManager;
import com.huohuo.dao.manager.GroupManager;
import com.huohuo.dao.table.Group;
import com.huohuo.mvp.contract.main.MainContract;
import com.huohuo.mvp.model.HuoHuoDataManager;
import com.huohuo.mvp.model.bean.ContractsBean;
import com.huohuo.mvp.model.bean.User;


import java.util.List;

import io.reactivex.Observable;

/**
 * Created by kennysun on 2019/8/7.
 */

public class MainPresenter extends HuoHuoBasePresenter<MainContract.View> implements MainContract.Presenter {
    @Override
    protected HuoHuoDataManager initDataManager() {
        return super.initDataManager();
    }

    /**
     * 更新本地好友、群聊信息
     */
    @Override
    public void updateGroupAndFriendInfo() {
        //merge交叉合并
        Observable.merge(dataManager.getFriends(), dataManager.getGroups())
                .compose(SchedulerUtil.ioToMain())
                .subscribe(httpResult -> {
                    if (httpResult.getCode() == ErrorStatus.SUCCESS) {
                        Object data = httpResult.getData();
                        LogUtil.d("MainPresenter:updateMsgInfo:data=$data");
                        if (data instanceof ContractsBean) {
                            //存储到本地--好友列表
                            ContractsBean contractsBean = (ContractsBean) data;
                            MmkvUtil.encodeBoolean(HuoHuoConstants.CONTRACT_NEW_APPLY, contractsBean.isNewApplyInfo());
                            DaoManager.getDaoManager().getFriendManager().insertList(contractsBean.getPersonalFriendInfoVOS());
                        } else if (data instanceof List) {
                            //存储到本地--群列表
                            List<Group> groups = (List<Group>) data;
                            DaoManager.getDaoManager().getGroupManager().insertList(groups);
                        }
                    } else {
                        handApiException(httpResult);
                    }
                }, throwable -> handException(throwable, ExceptionHandle.errorCode));

    }


    @Override
    public void getUser() {
        //todo 是否一个报错会终止整个--会
        //concat顺序合并
        Observable.concatArray(
                dataManager.getUserInfo(),
                dataManager.getUserAccount(),
                dataManager.getUserCommonSet(),
                dataManager.getUserPrivacySet(),
                dataManager.getUserSafeSet()
        ).compose(SchedulerUtil.ioToMain())
                .subscribe(httpResult -> {
                    LogUtil.d("MainPresenter:getUser:httpResult=" + httpResult);
                    if (httpResult.getCode() == ErrorStatus.SUCCESS) {
                        Object data = httpResult.getData();
                        if (data instanceof User.UserInfo) {
                            MmkvUtil.encodeLong(Constants.USER_ID, ((User.UserInfo) data).getId());
                            MmkvUtil.encodeString(Constants.USER_INFO, HuoHuoApp.gson.toJson(data));
                        } else if (data instanceof User.UserAccount) {
                            MmkvUtil.encodeString(Constants.USER_ACCOUNT, HuoHuoApp.gson.toJson(data));
                        } else if (data instanceof User.UserCommonSet) {
                            MmkvUtil.encodeString(Constants.USER_COMMON_SET, HuoHuoApp.gson.toJson(data));
                        } else if (data instanceof User.UserPrivacySet) {
                            MmkvUtil.encodeString(Constants.USER_PRIVACY_SET, HuoHuoApp.gson.toJson(data));
                        } else if (data instanceof User.UserSafeSet) {
                            MmkvUtil.encodeString(Constants.USER_SAFE_SET, HuoHuoApp.gson.toJson(data));
                        }
                    } else {
                        handApiException(httpResult);
                    }
                }, throwable -> {
                    handException(throwable, ExceptionHandle.errorCode);
                }, () -> {
                    //onComplete
                    LogUtil.d("MainPresenter:getUser:onComplete");
                    getMvpView().getUserSuccess();
                });
    }


}
