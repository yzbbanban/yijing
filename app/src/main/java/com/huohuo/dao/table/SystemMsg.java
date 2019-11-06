package com.huohuo.dao.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * 系统消息(好友申请、卡券消息)
 * Created by dian on 2018/11/29.
 * <p>
 * "id":"e22d7e2e-b4cb-48f7-8e8e-69ccc15f5496",
 * "fromUserId":2,
 * "toUserId":1,
 * "chatMessage":"{"systemType":2,"title":"您有新的好友申请","content":"嘻嘻嘻111请求添加您为好友","type":3}",
 * "messageType":22,
 * "messageStatus":0,
 * "sendTime":1543455181131
 * <p>
 * <p>
 * "id":"f0554030-c6af-410a-a4b5-4037aaaca3ef",
 * "fromUserId":0,
 * "fromUserName":"系统消息",
 * "toUserId":1,
 * "chatMessage":"{"systemType":1,"detailId":1234567,"title":"您有新的卡券到账","content":"邀请奖励-定期理财券~","type":2}",
 * "messageType":100,
 * "sendTime":1543454434548
 */

@Entity
public class SystemMsg {
    @Id
    private String msgId;
    @NotNull
    private Long id;//卡券id|好友id
    @NotNull
    private Integer messageType;
    @NotNull
    private Long sendTime;

    @NotNull
    private Integer systemType;//1卡券消息，2好友申请,3口令（自己发的）
    private String title;
    private String content;
    private Integer type;// // 1 是领,2 是查收

    private boolean showCount = true;//是否显示条数

    @Generated(hash = 1926525333)
    public SystemMsg(String msgId, @NotNull Long id, @NotNull Integer messageType, @NotNull Long sendTime,
                     @NotNull Integer systemType, String title, String content, Integer type, boolean showCount) {
        this.msgId = msgId;
        this.id = id;
        this.messageType = messageType;
        this.sendTime = sendTime;
        this.systemType = systemType;
        this.title = title;
        this.content = content;
        this.type = type;
        this.showCount = showCount;
    }

    @Generated(hash = 248475152)
    public SystemMsg() {
    }

    public String getMsgId() {
        return this.msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMessageType() {
        return this.messageType;
    }

    public void setMessageType(Integer messageType) {
        this.messageType = messageType;
    }

    public Long getSendTime() {
        return this.sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    public Integer getSystemType() {
        return this.systemType;
    }

    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return this.type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public boolean getShowCount() {
        return this.showCount;
    }

    public void setShowCount(boolean showCount) {
        this.showCount = showCount;
    }


}
