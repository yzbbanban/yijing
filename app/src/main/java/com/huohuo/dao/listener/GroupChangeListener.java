package com.huohuo.dao.listener;

import java.util.ArrayList;

/**
 * 群组相关的监听器，加群申请，被踢群等事件
 * Created by kennysun on 2019/8/8.
 */

public interface GroupChangeListener {
    /**
     * 当前用户收到加入群组邀请
     *  @param groupId    要加入的群的id
     * @param groupName 要加入的群的名称
     * @param inviter    邀请人的id
     */
    void onInvitationReceived(Long groupId, String groupName,Long inviter);

    /**
     * 用户申请加入群
     *   @param groupId    要加入的群的id
     * @param groupName 要加入的群的名称
     * @param applicant    申请人的id
     */
    void onRequestToJoinReceived(Long groupId,String groupName,Long applicant);

    /**
     * 群组邀请被接受
     *  @param groupId
     * @param invitee 被邀请人
     */
    void onInvitationAccepted(Long groupId, Long invitee);

    /**
     * 当前登录用户被管理员移除出群组
     */
    void onUserRemoved(Long groupId,String groupName);

    /**
     * 群组被解散
     * 会先删除本地的这个群组，之后通过此回调通知应用，此群组被删除了
     */
    void onGroupDestroyed(Long groupId, String groupName);

    /**
     * 有成员被禁言
     * @param groupId    产生禁言的群组id
     * @param mutes    被禁言的成员列表
     */
    void onMuteListAdded(Long groupId,ArrayList<Long> mutes);

    /**
     * 有成员从禁言列表中移除，恢复发言权限
     * @param groupId    产生禁言的群组id
     * @param mutes    有成员从群组禁言列表中移除
     */
    void onMuteListRemoved(Long groupId,ArrayList<Long> mutes);

    /**
     * 群组加入新成员事件
     * @param groupId 群组id
     * @param member 新成员id
     */
    void onMemberJoined(Long groupId,Long member);

    /**
     * 群组成员主动退出事件
     *  @param groupId 群组id
     * @param member 退出的成员的id
     */
    void onMemberExited(Long groupId,Long member);
}
