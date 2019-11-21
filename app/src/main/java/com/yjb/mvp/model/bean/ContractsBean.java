package com.yjb.mvp.model.bean;

import com.yjb.dao.table.Friend;

import java.util.List;

/**
 * Created by kennysun on 2019/9/3.
 */

public class ContractsBean {
    /**
     * "newApplyInfo": false,
     * "personalFriendInfoVOS":
     */
    private boolean newApplyInfo;
    private List<Friend> personalFriendInfoVOS;

    public boolean isNewApplyInfo() {
        return newApplyInfo;
    }

    public void setNewApplyInfo(boolean newApplyInfo) {
        this.newApplyInfo = newApplyInfo;
    }

    public List<Friend> getPersonalFriendInfoVOS() {
        return personalFriendInfoVOS;
    }

    public void setPersonalFriendInfoVOS(List<Friend> personalFriendInfoVOS) {
        this.personalFriendInfoVOS = personalFriendInfoVOS;
    }
}
