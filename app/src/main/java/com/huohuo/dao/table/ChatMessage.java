package com.huohuo.dao.table;


import com.huohuo.app.HuoHuoConstants;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * Created by dianer on 2018/9/8.
 */

@Entity
public class ChatMessage {
    @Id
    private String id;//消息id
    @NotNull
    private Integer fromUserId;//发送方用户ID
    @NotNull
    private String fromUserName;//发送方用户名称（昵称或备注）
    private String fromUserNickName;//发送方用户昵称
    private String fromUserHeadImage;//发送方用户头像
    private Integer toUserId;//接收方用户ID
    private String toUserName;//接收方用户ID
    private String toUserHeadImage;//接收方用户ID
    @NotNull
    private String chatMessage;//聊天内容
    @NotNull
    private Integer messageType;//消息类型（-2不是好友 -1消息发送失败 1聊天消息 2好友添加成功 3红包消息 10消息确认 13已领取红包 20好友申请）
    @NotNull
    private Integer viewMessageType;//展示的消息类型：1聊天消息  3红包消息
    @NotNull
    private Long sendTime;//发送时间
    @NotNull
    private Boolean readed = false;//是否已读
    private Boolean useNoDisturb = false;//是否免打扰
    private int handType = HuoHuoConstants.HANDTYPE_NEW;//eventbus处理类型
    private boolean receiverd = false;//是否已领取红包
    private boolean showTime = false;//是否展示时间
    private int msgState = HuoHuoConstants.sending;//消息发送的状态


    //群
    private String groupHeadImage;
    private String groupName;
    private Long groupId;
    private String notifyUser;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public boolean isShowTime() {
        return showTime;
    }

    public void setShowTime(boolean showTime) {
        this.showTime = showTime;
    }

    @Generated(hash = 238777590)
    public ChatMessage(String id, @NotNull Integer fromUserId, @NotNull String fromUserName,
            String fromUserNickName, String fromUserHeadImage, Integer toUserId, String toUserName,
            String toUserHeadImage, @NotNull String chatMessage, @NotNull Integer messageType,
            @NotNull Integer viewMessageType, @NotNull Long sendTime, @NotNull Boolean readed,
            Boolean useNoDisturb, int handType, boolean receiverd, boolean showTime, int msgState,
            String groupHeadImage, String groupName, Long groupId, String notifyUser) {
        this.id = id;
        this.fromUserId = fromUserId;
        this.fromUserName = fromUserName;
        this.fromUserNickName = fromUserNickName;
        this.fromUserHeadImage = fromUserHeadImage;
        this.toUserId = toUserId;
        this.toUserName = toUserName;
        this.toUserHeadImage = toUserHeadImage;
        this.chatMessage = chatMessage;
        this.messageType = messageType;
        this.viewMessageType = viewMessageType;
        this.sendTime = sendTime;
        this.readed = readed;
        this.useNoDisturb = useNoDisturb;
        this.handType = handType;
        this.receiverd = receiverd;
        this.showTime = showTime;
        this.msgState = msgState;
        this.groupHeadImage = groupHeadImage;
        this.groupName = groupName;
        this.groupId = groupId;
        this.notifyUser = notifyUser;
    }

    @Generated(hash = 2271208)
    public ChatMessage() {
    }

//    public boolean isLoding() {
//        return loding;
//    }
//
//    public void setLoding(boolean loding) {
//        this.loding = loding;
//    }

    public boolean isReceiverd() {
        return receiverd;
    }

    public void setReceiverd(boolean receiverd) {
        this.receiverd = receiverd;
    }

  

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getFromUserHeadImage() {
        return fromUserHeadImage;
    }

    public void setFromUserHeadImage(String fromUserHeadImage) {
        this.fromUserHeadImage = fromUserHeadImage;
    }

    public Integer getToUserId() {
        return toUserId;
    }

    public void setToUserId(Integer toUserId) {
        this.toUserId = toUserId;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }

    public Integer getMessageType() {
        return messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public Integer getViewMessageType() {
        return viewMessageType;
    }

    public void setViewMessageType(Integer viewMessageType) {
        this.viewMessageType = viewMessageType;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public Boolean getReaded() {
        return readed;
    }

    public void setReaded(Boolean readed) {
        this.readed = readed;
    }

    public int getHandType() {
        return handType;
    }

    public void setHandType(int handType) {
        this.handType = handType;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getToUserHeadImage() {
        return toUserHeadImage;
    }

    public void setToUserHeadImage(String toUserHeadImage) {
        this.toUserHeadImage = toUserHeadImage;
    }

    public Boolean getUseNoDisturb() {
        return useNoDisturb;
    }

    public void setUseNoDisturb(Boolean useNoDisturb) {
        this.useNoDisturb = useNoDisturb;
    }

    public boolean getReceiverd() {
        return this.receiverd;
    }

    public boolean getShowTime() {
        return this.showTime;
    }

    public int getMsgState() {
        return this.msgState;
    }

    public void setMsgState(int msgState) {
        this.msgState = msgState;
    }

    public String getFromUserNickName() {
        return this.fromUserNickName;
    }

    public void setFromUserNickName(String fromUserNickName) {
        this.fromUserNickName = fromUserNickName;
    }

    public String getGroupHeadImage() {
        return this.groupHeadImage;
    }

    public void setGroupHeadImage(String groupHeadImage) {
        this.groupHeadImage = groupHeadImage;
    }

    public String getNotifyUser() {
        return this.notifyUser;
    }

    public void setNotifyUser(String notifyUser) {
        this.notifyUser = notifyUser;
    }

}
