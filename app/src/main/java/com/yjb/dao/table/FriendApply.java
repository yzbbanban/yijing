package com.yjb.dao.table;

import com.yjb.app.HuoHuoConstants;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * 好友申请  &  系统消息
 * Created by dian on 2018/11/15.
 */
@Entity
public class FriendApply {
    @Id
    private Long id;//好友id
    private String nickName;
    private String avatar;
    @NotNull
    private Long time;//消息时间
    private boolean showCount = true;//是否显示个数,用于在会话列表上面展示的个数
    @NotNull
    private Integer messageType = HuoHuoConstants.APPLY;//消息类型
    private String message;//消息内容
    private Integer handledType = 0;//消息是否已处理:0未处理、好友申请（1同意，2拒绝）、卡券消息（3已处理）

    @Generated(hash = 387449280)
    public FriendApply(Long id, String nickName, String avatar, @NotNull Long time,
            boolean showCount, @NotNull Integer messageType, String message,
            Integer handledType) {
        this.id = id;
        this.nickName = nickName;
        this.avatar = avatar;
        this.time = time;
        this.showCount = showCount;
        this.messageType = messageType;
        this.message = message;
        this.handledType = handledType;
    }

    @Generated(hash = 441049216)
    public FriendApply() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Long getTime() {
        return this.time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public boolean getShowCount() {
        return this.showCount;
    }

    public void setShowCount(boolean showCount) {
        this.showCount = showCount;
    }

    public Integer getMessageType() {
        return this.messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getHandledType() {
        return this.handledType;
    }

    public void setHandledType(Integer handledType) {
        this.handledType = handledType;
    }

}
