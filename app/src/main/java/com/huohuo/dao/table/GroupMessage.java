package com.huohuo.dao.table;

import com.huohuo.app.HuoHuoConstants;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * 群消息
 * Created by dian on 2018/11/24.
 */
@Entity
public class GroupMessage {
    @Id
    private String id;//消息id
    private String firstMessageId;//消息id
    private String lastMessageId;//消息id
    @NotNull
    private Integer fromUserId;//发送方用户ID
    private String fromUserName;//发送方用户名称（昵称或备注）
    private String fromUserNickName;//发送方用户昵称
    private String fromUserHeadImage;//发送方用户头像
    @NotNull
    private Long groupId;//接收方用户ID
    private String groupName;//接收方用户ID
    private String groupHeadImage;//接收方用户ID
    private String chatMessage;//聊天内容
    @NotNull
    private Integer messageType;//消息类型（-2不是好友 -1消息发送失败 1聊天消息 2好友添加成功 3红包消息 10消息确认 13已领取红包 20好友申请）
    private Integer viewMessageType;//展示的消息类型：1聊天消息  3红包消息
    @NotNull
    private Long sendTime;//发送时间
    private Long unreadCount;//未读消息数量
    private Boolean readed = false;//是否已读
    private Boolean useNoDisturb = false;//是否免打扰
    private int handType = HuoHuoConstants.HANDTYPE_NEW;//eventbus处理类型
    private boolean receiverd = false;//是否已领取红包
    private boolean qiangDao = false;//是否抢到红包
    private boolean showTime = false;//是否展示时间
    private int msgState = HuoHuoConstants.sending;//消息发送的状态
    private String notifyUser;
    @Generated(hash = 1947199760)
    public GroupMessage(String id, String firstMessageId, String lastMessageId,
                        @NotNull Integer fromUserId, String fromUserName, String fromUserNickName,
                        String fromUserHeadImage, @NotNull Long groupId, String groupName, String groupHeadImage,
                        String chatMessage, @NotNull Integer messageType, Integer viewMessageType,
                        @NotNull Long sendTime, Long unreadCount, Boolean readed, Boolean useNoDisturb,
                        int handType, boolean receiverd, boolean qiangDao, boolean showTime, int msgState,
                        String notifyUser) {
        this.id = id;
        this.firstMessageId = firstMessageId;
        this.lastMessageId = lastMessageId;
        this.fromUserId = fromUserId;
        this.fromUserName = fromUserName;
        this.fromUserNickName = fromUserNickName;
        this.fromUserHeadImage = fromUserHeadImage;
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupHeadImage = groupHeadImage;
        this.chatMessage = chatMessage;
        this.messageType = messageType;
        this.viewMessageType = viewMessageType;
        this.sendTime = sendTime;
        this.unreadCount = unreadCount;
        this.readed = readed;
        this.useNoDisturb = useNoDisturb;
        this.handType = handType;
        this.receiverd = receiverd;
        this.qiangDao = qiangDao;
        this.showTime = showTime;
        this.msgState = msgState;
        this.notifyUser = notifyUser;
    }
    @Generated(hash = 159954481)
    public GroupMessage() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Integer getFromUserId() {
        return this.fromUserId;
    }
    public void setFromUserId(Integer fromUserId) {
        this.fromUserId = fromUserId;
    }
    public String getFromUserName() {
        return this.fromUserName;
    }
    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }
    public String getFromUserNickName() {
        return this.fromUserNickName;
    }
    public void setFromUserNickName(String fromUserNickName) {
        this.fromUserNickName = fromUserNickName;
    }
    public String getFromUserHeadImage() {
        return this.fromUserHeadImage;
    }
    public void setFromUserHeadImage(String fromUserHeadImage) {
        this.fromUserHeadImage = fromUserHeadImage;
    }
    public Long getGroupId() {
        return this.groupId;
    }
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
    public String getGroupName() {
        return this.groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public String getGroupHeadImage() {
        return this.groupHeadImage;
    }
    public void setGroupHeadImage(String groupHeadImage) {
        this.groupHeadImage = groupHeadImage;
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
    public Long getUnreadCount() {
        return this.unreadCount;
    }
    public void setUnreadCount(Long unreadCount) {
        this.unreadCount = unreadCount;
    }
    public Boolean getReaded() {
        return this.readed;
    }
    public void setReaded(Boolean readed) {
        this.readed = readed;
    }
    public Boolean getUseNoDisturb() {
        return this.useNoDisturb;
    }
    public void setUseNoDisturb(Boolean useNoDisturb) {
        this.useNoDisturb = useNoDisturb;
    }
    public int getHandType() {
        return this.handType;
    }
    public void setHandType(int handType) {
        this.handType = handType;
    }
    public boolean getReceiverd() {
        return this.receiverd;
    }
    public void setReceiverd(boolean receiverd) {
        this.receiverd = receiverd;
    }
    public boolean getQiangDao() {
        return this.qiangDao;
    }
    public void setQiangDao(boolean qiangDao) {
        this.qiangDao = qiangDao;
    }
    public boolean getShowTime() {
        return this.showTime;
    }
    public void setShowTime(boolean showTime) {
        this.showTime = showTime;
    }
    public int getMsgState() {
        return this.msgState;
    }
    public void setMsgState(int msgState) {
        this.msgState = msgState;
    }
    public String getFirstMessageId() {
        return this.firstMessageId;
    }
    public void setFirstMessageId(String firstMessageId) {
        this.firstMessageId = firstMessageId;
    }
    public String getLastMessageId() {
        return this.lastMessageId;
    }
    public void setLastMessageId(String lastMessageId) {
        this.lastMessageId = lastMessageId;
    }
    public String getNotifyUser() {
        return this.notifyUser;
    }
    public void setNotifyUser(String notifyUser) {
        this.notifyUser = notifyUser;
    }

}
