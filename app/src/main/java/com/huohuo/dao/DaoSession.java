package com.huohuo.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.huohuo.dao.table.FriendApply;
import com.huohuo.dao.table.SystemMsg;
import com.huohuo.dao.table.Group;
import com.huohuo.dao.table.Friend;
import com.huohuo.dao.table.ChatList;
import com.huohuo.dao.table.GroupMessage;
import com.huohuo.dao.table.ChatMessage;

import com.huohuo.dao.FriendApplyDao;
import com.huohuo.dao.SystemMsgDao;
import com.huohuo.dao.GroupDao;
import com.huohuo.dao.FriendDao;
import com.huohuo.dao.ChatListDao;
import com.huohuo.dao.GroupMessageDao;
import com.huohuo.dao.ChatMessageDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig friendApplyDaoConfig;
    private final DaoConfig systemMsgDaoConfig;
    private final DaoConfig groupDaoConfig;
    private final DaoConfig friendDaoConfig;
    private final DaoConfig chatListDaoConfig;
    private final DaoConfig groupMessageDaoConfig;
    private final DaoConfig chatMessageDaoConfig;

    private final FriendApplyDao friendApplyDao;
    private final SystemMsgDao systemMsgDao;
    private final GroupDao groupDao;
    private final FriendDao friendDao;
    private final ChatListDao chatListDao;
    private final GroupMessageDao groupMessageDao;
    private final ChatMessageDao chatMessageDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        friendApplyDaoConfig = daoConfigMap.get(FriendApplyDao.class).clone();
        friendApplyDaoConfig.initIdentityScope(type);

        systemMsgDaoConfig = daoConfigMap.get(SystemMsgDao.class).clone();
        systemMsgDaoConfig.initIdentityScope(type);

        groupDaoConfig = daoConfigMap.get(GroupDao.class).clone();
        groupDaoConfig.initIdentityScope(type);

        friendDaoConfig = daoConfigMap.get(FriendDao.class).clone();
        friendDaoConfig.initIdentityScope(type);

        chatListDaoConfig = daoConfigMap.get(ChatListDao.class).clone();
        chatListDaoConfig.initIdentityScope(type);

        groupMessageDaoConfig = daoConfigMap.get(GroupMessageDao.class).clone();
        groupMessageDaoConfig.initIdentityScope(type);

        chatMessageDaoConfig = daoConfigMap.get(ChatMessageDao.class).clone();
        chatMessageDaoConfig.initIdentityScope(type);

        friendApplyDao = new FriendApplyDao(friendApplyDaoConfig, this);
        systemMsgDao = new SystemMsgDao(systemMsgDaoConfig, this);
        groupDao = new GroupDao(groupDaoConfig, this);
        friendDao = new FriendDao(friendDaoConfig, this);
        chatListDao = new ChatListDao(chatListDaoConfig, this);
        groupMessageDao = new GroupMessageDao(groupMessageDaoConfig, this);
        chatMessageDao = new ChatMessageDao(chatMessageDaoConfig, this);

        registerDao(FriendApply.class, friendApplyDao);
        registerDao(SystemMsg.class, systemMsgDao);
        registerDao(Group.class, groupDao);
        registerDao(Friend.class, friendDao);
        registerDao(ChatList.class, chatListDao);
        registerDao(GroupMessage.class, groupMessageDao);
        registerDao(ChatMessage.class, chatMessageDao);
    }
    
    public void clear() {
        friendApplyDaoConfig.clearIdentityScope();
        systemMsgDaoConfig.clearIdentityScope();
        groupDaoConfig.clearIdentityScope();
        friendDaoConfig.clearIdentityScope();
        chatListDaoConfig.clearIdentityScope();
        groupMessageDaoConfig.clearIdentityScope();
        chatMessageDaoConfig.clearIdentityScope();
    }

    public FriendApplyDao getFriendApplyDao() {
        return friendApplyDao;
    }

    public SystemMsgDao getSystemMsgDao() {
        return systemMsgDao;
    }

    public GroupDao getGroupDao() {
        return groupDao;
    }

    public FriendDao getFriendDao() {
        return friendDao;
    }

    public ChatListDao getChatListDao() {
        return chatListDao;
    }

    public GroupMessageDao getGroupMessageDao() {
        return groupMessageDao;
    }

    public ChatMessageDao getChatMessageDao() {
        return chatMessageDao;
    }

}
