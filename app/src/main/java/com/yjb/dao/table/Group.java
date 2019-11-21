package com.yjb.dao.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

/**
 * 群
 * Created by dian on 2018/12/5.
 */

@Entity
public class Group {
    /**
     * "id":73,
     * "groupName":"袁点点186",
     * "groupAutoHead":"v1/image/group/head/294ab3ff-a02d-4837-88e8-1d01cddddb75.jpg",//群头像（合成）
     * "groupAssignHead":null,//群头像（自定义）
     * "userId":56,
     * "allowNoDisturb":false,
     * "allowUserSpeak":true,
     * "allowEachAddFriend":false,
     * "allowSpeak":true
     */

    @Id
    private Long id;
    private Integer userId;
    @NotNull
    private String groupName;
    private String groupAutoHead;
    private String groupAssignHead;
    private boolean allowNoDisturb = false;
    private boolean allowUserSpeak = true;
    private boolean allowEachAddFriend = true;
    private boolean allowSpeak = true;
    private boolean jiesan = false;
    @Generated(hash = 622880141)
    public Group(Long id, Integer userId, @NotNull String groupName, String groupAutoHead,
            String groupAssignHead, boolean allowNoDisturb, boolean allowUserSpeak,
            boolean allowEachAddFriend, boolean allowSpeak, boolean jiesan) {
        this.id = id;
        this.userId = userId;
        this.groupName = groupName;
        this.groupAutoHead = groupAutoHead;
        this.groupAssignHead = groupAssignHead;
        this.allowNoDisturb = allowNoDisturb;
        this.allowUserSpeak = allowUserSpeak;
        this.allowEachAddFriend = allowEachAddFriend;
        this.allowSpeak = allowSpeak;
        this.jiesan = jiesan;
    }
    @Generated(hash = 117982048)
    public Group() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Integer getUserId() {
        return this.userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getGroupName() {
        return this.groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public String getGroupAutoHead() {
        return this.groupAutoHead;
    }
    public void setGroupAutoHead(String groupAutoHead) {
        this.groupAutoHead = groupAutoHead;
    }
    public String getGroupAssignHead() {
        return this.groupAssignHead;
    }
    public void setGroupAssignHead(String groupAssignHead) {
        this.groupAssignHead = groupAssignHead;
    }
    public boolean getAllowNoDisturb() {
        return this.allowNoDisturb;
    }
    public void setAllowNoDisturb(boolean allowNoDisturb) {
        this.allowNoDisturb = allowNoDisturb;
    }
    public boolean getAllowUserSpeak() {
        return this.allowUserSpeak;
    }
    public void setAllowUserSpeak(boolean allowUserSpeak) {
        this.allowUserSpeak = allowUserSpeak;
    }
    public boolean getAllowEachAddFriend() {
        return this.allowEachAddFriend;
    }
    public void setAllowEachAddFriend(boolean allowEachAddFriend) {
        this.allowEachAddFriend = allowEachAddFriend;
    }
    public boolean getAllowSpeak() {
        return this.allowSpeak;
    }
    public void setAllowSpeak(boolean allowSpeak) {
        this.allowSpeak = allowSpeak;
    }
    public boolean getJiesan() {
        return this.jiesan;
    }
    public void setJiesan(boolean jiesan) {
        this.jiesan = jiesan;
    }
    
}
