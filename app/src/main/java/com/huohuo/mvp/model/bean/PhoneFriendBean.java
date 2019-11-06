package com.huohuo.mvp.model.bean;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * 通讯录好友
 * Created by kennysun on 2019/9/3.
 */

public class PhoneFriendBean implements Serializable, Comparable<PhoneFriendBean>{
    /**
     * *  "id": 用户id,
     * "friendUid": 对方id,
     * "mobile": 对方手机号码,
     * "nickName": 对方昵称,    ---昵称不为空：显示昵称 空：显示手机号
     * "realName": 对方真实姓名,
     * "headImage": 对方头像路径,
     * "friendRemark": 对方备注,
     * "becomeFriend": 是否已成为好友,
     * "useNoDisturb": 是否拒收消息,
     * "useAddFriendForMe": 是否拒绝添加我为好友
     */

    private String firstNameLetter;//昵称或备注首大写字母
    private int id;
    private Long friendUid;
    private String mobile;
    private String nickName;
    private String realName;
    private String headImage;
    private String friendRemark;
    private boolean becomeFriend;//是否已成为好友
    private boolean useNoDisturb;//是否拒收消息
    private boolean useAddFriendForMe;//是否拒绝添加我为好友
    private boolean isRegister;     //是否注册过btw

    public String getFirstNameLetter() {
        return firstNameLetter;
    }

    public void setFirstNameLetter(String firstNameLetter) {
        this.firstNameLetter = firstNameLetter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getFriendUid() {
        return friendUid;
    }

    public void setFriendUid(Long friendUid) {
        this.friendUid = friendUid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getFriendRemark() {
        return friendRemark;
    }

    public void setFriendRemark(String friendRemark) {
        this.friendRemark = friendRemark;
    }

    public boolean isBecomeFriend() {
        return becomeFriend;
    }

    public void setBecomeFriend(boolean becomeFriend) {
        this.becomeFriend = becomeFriend;
    }

    public boolean isUseNoDisturb() {
        return useNoDisturb;
    }

    public void setUseNoDisturb(boolean useNoDisturb) {
        this.useNoDisturb = useNoDisturb;
    }

    public boolean isUseAddFriendForMe() {
        return useAddFriendForMe;
    }

    public void setUseAddFriendForMe(boolean useAddFriendForMe) {
        this.useAddFriendForMe = useAddFriendForMe;
    }

    @Override
    public int compareTo(@NonNull PhoneFriendBean friend) {
        return this.firstNameLetter.compareTo(friend.firstNameLetter);
    }

    public boolean isRegister() {
        return isRegister;
    }

    public void setRegister(boolean register) {
        isRegister = register;
    }
}
