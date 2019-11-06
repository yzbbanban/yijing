package com.huohuo.dao.table;

import android.support.annotation.NonNull;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * 通讯录好友表
 * Created by dian on 2018/11/8.
 * /**
 * "useAddFriendForMe": 是否拒绝添加我为好友
 *
 *
 "id":"2",
 "friendUid":"2",
 "mobile":"18608323570",
 "nickName":"点点186",
 "realName":"袁敏",
 "headImage":"v1/head/2/2a7d6b06cadb4d25bc02ecf3b128f891.jpg",
 "friendRemark":null,
 "becomeFriend":false,
 "useNoDisturb":false,
 "useAddFriendForMe": 是否拒绝添加我为好友
 */

@Entity
public class Friend implements Comparable<Friend>{

    @Id
    private Long friendUid;//朋友id
    private Long id;//我的id
    @NotNull
    private String mobile;//手机号
    private String realName;//手机号
    @NotNull
    private String firstChar;//备注|昵称首字母
    private String friendRemark;//备注
    @NotNull
    private String nickName;//昵称
    @NotNull
    private String headImage;
    private boolean becomeFriend;
    private boolean useAddFriendForMe;
    private boolean register;
    @NotNull
    private Boolean useNoDisturb = false;//是否拒收消息
    @Generated(hash = 1796522095)
    public Friend(Long friendUid, Long id, @NotNull String mobile, String realName,
            @NotNull String firstChar, String friendRemark,
            @NotNull String nickName, @NotNull String headImage,
            boolean becomeFriend, boolean useAddFriendForMe, boolean register,
            @NotNull Boolean useNoDisturb) {
        this.friendUid = friendUid;
        this.id = id;
        this.mobile = mobile;
        this.realName = realName;
        this.firstChar = firstChar;
        this.friendRemark = friendRemark;
        this.nickName = nickName;
        this.headImage = headImage;
        this.becomeFriend = becomeFriend;
        this.useAddFriendForMe = useAddFriendForMe;
        this.register = register;
        this.useNoDisturb = useNoDisturb;
    }
    @Generated(hash = 287143722)
    public Friend() {
    }
    public Long getFriendUid() {
        return this.friendUid;
    }
    public void setFriendUid(Long friendUid) {
        this.friendUid = friendUid;
    }
    public String getMobile() {
        return this.mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getRealName() {
        return this.realName;
    }
    public void setRealName(String realName) {
        this.realName = realName;
    }
    public String getFirstChar() {
        return this.firstChar;
    }
    public void setFirstChar(String firstChar) {
        this.firstChar = firstChar;
    }
    public String getFriendRemark() {
        return this.friendRemark;
    }
    public void setFriendRemark(String friendRemark) {
        this.friendRemark = friendRemark;
    }
    public String getNickName() {
        return this.nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getHeadImage() {
        return this.headImage;
    }
    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }
    public boolean getBecomeFriend() {
        return this.becomeFriend;
    }
    public void setBecomeFriend(boolean becomeFriend) {
        this.becomeFriend = becomeFriend;
    }
    public boolean getUseAddFriendForMe() {
        return this.useAddFriendForMe;
    }
    public void setUseAddFriendForMe(boolean useAddFriendForMe) {
        this.useAddFriendForMe = useAddFriendForMe;
    }
    public boolean getRegister() {
        return this.register;
    }
    public void setRegister(boolean register) {
        this.register = register;
    }
    public Boolean getUseNoDisturb() {
        return this.useNoDisturb;
    }
    public void setUseNoDisturb(Boolean useNoDisturb) {
        this.useNoDisturb = useNoDisturb;
    }

    @Override
    public int compareTo(@NonNull Friend o) {
        return this.firstChar.compareTo(o.firstChar);
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
