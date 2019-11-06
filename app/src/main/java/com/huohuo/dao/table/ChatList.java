package com.huohuo.dao.table;

import com.huohuo.app.HuoHuoConstants;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * 创建人： 张蕴聪
 * 时间： 2018\9\12 0012
 */

@Entity(
        indexes = {
                @Index(value = "id,isGroup", unique = true)
        }
)
public class ChatList {
    private Long id;//发送方用户ID(如果是群的话，就直接存群id)
    @NotNull
    private Boolean isGroup = false;//是否是群
    @NotNull
    private String fromUserName;//发送方用户名称
    private String fromUserHeadImage;//发送方用户头像(如果是群的话，则为空)
    @NotNull
    private String chatMessage;//聊天内容
    @NotNull
    private Integer messageType;//消息类型（-1消息发送失败 1聊天消息 2好友添加成功 3红包消息 10消息确认 20好友申请）
    @NotNull
    private Integer viewMessageType;//展示的消息类型：1聊天消息  3红包消息
    @NotNull
    private Long sendTime;//发送时间
    @NotNull
    private Boolean messageQuiet = false;//消息免打扰
    @NotNull
    private Boolean readed = false;//是否已读
    private Integer count = 0;//未读数量
    private int msgState = HuoHuoConstants.sending;//消息发送的状态
    private String notifyUser;//艾特谁，逗号隔开的id字符串
    @Generated(hash = 967204254)
    public ChatList(Long id, @NotNull Boolean isGroup, @NotNull String fromUserName,
            String fromUserHeadImage, @NotNull String chatMessage,
            @NotNull Integer messageType, @NotNull Integer viewMessageType,
            @NotNull Long sendTime, @NotNull Boolean messageQuiet,
            @NotNull Boolean readed, Integer count, int msgState,
            String notifyUser) {
        this.id = id;
        this.isGroup = isGroup;
        this.fromUserName = fromUserName;
        this.fromUserHeadImage = fromUserHeadImage;
        this.chatMessage = chatMessage;
        this.messageType = messageType;
        this.viewMessageType = viewMessageType;
        this.sendTime = sendTime;
        this.messageQuiet = messageQuiet;
        this.readed = readed;
        this.count = count;
        this.msgState = msgState;
        this.notifyUser = notifyUser;
    }

    @Generated(hash = 406825685)
    public ChatList() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsGroup() {
        return this.isGroup;
    }

    public void setIsGroup(Boolean isGroup) {
        this.isGroup = isGroup;
    }

    public String getFromUserName() {
        return this.fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getFromUserHeadImage() {
        return this.fromUserHeadImage;
    }

    public void setFromUserHeadImage(String fromUserHeadImage) {
        this.fromUserHeadImage = fromUserHeadImage;
    }

    public String getChatMessage() {
        return this.chatMessage;
    }

    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }

    public Integer getMessageType() {
        return this.messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public Integer getViewMessageType() {
        return this.viewMessageType;
    }

    public void setViewMessageType(Integer viewMessageType) {
        this.viewMessageType = viewMessageType;
    }

    public Long getSendTime() {
        return this.sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public Boolean getMessageQuiet() {
        return this.messageQuiet;
    }

    public void setMessageQuiet(Boolean messageQuiet) {
        this.messageQuiet = messageQuiet;
    }

    public Boolean getReaded() {
        return this.readed;
    }

    public void setReaded(Boolean readed) {
        this.readed = readed;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public int getMsgState() {
        return this.msgState;
    }

    public void setMsgState(int msgState) {
        this.msgState = msgState;
    }

    public String getNotifyUser() {
        return this.notifyUser;
    }

    public void setNotifyUser(String notifyUser) {
        this.notifyUser = notifyUser;
    }

}
