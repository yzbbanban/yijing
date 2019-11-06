package com.huohuo.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.huohuo.dao.table.ChatList;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CHAT_LIST".
*/
public class ChatListDao extends AbstractDao<ChatList, Void> {

    public static final String TABLENAME = "CHAT_LIST";

    /**
     * Properties of entity ChatList.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", false, "ID");
        public final static Property IsGroup = new Property(1, Boolean.class, "isGroup", false, "IS_GROUP");
        public final static Property FromUserName = new Property(2, String.class, "fromUserName", false, "FROM_USER_NAME");
        public final static Property FromUserHeadImage = new Property(3, String.class, "fromUserHeadImage", false, "FROM_USER_HEAD_IMAGE");
        public final static Property ChatMessage = new Property(4, String.class, "chatMessage", false, "CHAT_MESSAGE");
        public final static Property MessageType = new Property(5, Integer.class, "messageType", false, "MESSAGE_TYPE");
        public final static Property ViewMessageType = new Property(6, Integer.class, "viewMessageType", false, "VIEW_MESSAGE_TYPE");
        public final static Property SendTime = new Property(7, Long.class, "sendTime", false, "SEND_TIME");
        public final static Property MessageQuiet = new Property(8, Boolean.class, "messageQuiet", false, "MESSAGE_QUIET");
        public final static Property Readed = new Property(9, Boolean.class, "readed", false, "READED");
        public final static Property Count = new Property(10, Integer.class, "count", false, "COUNT");
        public final static Property MsgState = new Property(11, int.class, "msgState", false, "MSG_STATE");
        public final static Property NotifyUser = new Property(12, String.class, "notifyUser", false, "NOTIFY_USER");
    }


    public ChatListDao(DaoConfig config) {
        super(config);
    }
    
    public ChatListDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CHAT_LIST\" (" + //
                "\"ID\" INTEGER," + // 0: id
                "\"IS_GROUP\" INTEGER NOT NULL ," + // 1: isGroup
                "\"FROM_USER_NAME\" TEXT NOT NULL ," + // 2: fromUserName
                "\"FROM_USER_HEAD_IMAGE\" TEXT," + // 3: fromUserHeadImage
                "\"CHAT_MESSAGE\" TEXT NOT NULL ," + // 4: chatMessage
                "\"MESSAGE_TYPE\" INTEGER NOT NULL ," + // 5: messageType
                "\"VIEW_MESSAGE_TYPE\" INTEGER NOT NULL ," + // 6: viewMessageType
                "\"SEND_TIME\" INTEGER NOT NULL ," + // 7: sendTime
                "\"MESSAGE_QUIET\" INTEGER NOT NULL ," + // 8: messageQuiet
                "\"READED\" INTEGER NOT NULL ," + // 9: readed
                "\"COUNT\" INTEGER," + // 10: count
                "\"MSG_STATE\" INTEGER NOT NULL ," + // 11: msgState
                "\"NOTIFY_USER\" TEXT);"); // 12: notifyUser
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_CHAT_LIST_ID_IS_GROUP ON \"CHAT_LIST\"" +
                " (\"ID\" ASC,\"IS_GROUP\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CHAT_LIST\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ChatList entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getIsGroup() ? 1L: 0L);
        stmt.bindString(3, entity.getFromUserName());
 
        String fromUserHeadImage = entity.getFromUserHeadImage();
        if (fromUserHeadImage != null) {
            stmt.bindString(4, fromUserHeadImage);
        }
        stmt.bindString(5, entity.getChatMessage());
        stmt.bindLong(6, entity.getMessageType());
        stmt.bindLong(7, entity.getViewMessageType());
        stmt.bindLong(8, entity.getSendTime());
        stmt.bindLong(9, entity.getMessageQuiet() ? 1L: 0L);
        stmt.bindLong(10, entity.getReaded() ? 1L: 0L);
 
        Integer count = entity.getCount();
        if (count != null) {
            stmt.bindLong(11, count);
        }
        stmt.bindLong(12, entity.getMsgState());
 
        String notifyUser = entity.getNotifyUser();
        if (notifyUser != null) {
            stmt.bindString(13, notifyUser);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ChatList entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindLong(2, entity.getIsGroup() ? 1L: 0L);
        stmt.bindString(3, entity.getFromUserName());
 
        String fromUserHeadImage = entity.getFromUserHeadImage();
        if (fromUserHeadImage != null) {
            stmt.bindString(4, fromUserHeadImage);
        }
        stmt.bindString(5, entity.getChatMessage());
        stmt.bindLong(6, entity.getMessageType());
        stmt.bindLong(7, entity.getViewMessageType());
        stmt.bindLong(8, entity.getSendTime());
        stmt.bindLong(9, entity.getMessageQuiet() ? 1L: 0L);
        stmt.bindLong(10, entity.getReaded() ? 1L: 0L);
 
        Integer count = entity.getCount();
        if (count != null) {
            stmt.bindLong(11, count);
        }
        stmt.bindLong(12, entity.getMsgState());
 
        String notifyUser = entity.getNotifyUser();
        if (notifyUser != null) {
            stmt.bindString(13, notifyUser);
        }
    }

    @Override
    public Void readKey(Cursor cursor, int offset) {
        return null;
    }    

    @Override
    public ChatList readEntity(Cursor cursor, int offset) {
        ChatList entity = new ChatList( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getShort(offset + 1) != 0, // isGroup
            cursor.getString(offset + 2), // fromUserName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // fromUserHeadImage
            cursor.getString(offset + 4), // chatMessage
            cursor.getInt(offset + 5), // messageType
            cursor.getInt(offset + 6), // viewMessageType
            cursor.getLong(offset + 7), // sendTime
            cursor.getShort(offset + 8) != 0, // messageQuiet
            cursor.getShort(offset + 9) != 0, // readed
            cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10), // count
            cursor.getInt(offset + 11), // msgState
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12) // notifyUser
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ChatList entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setIsGroup(cursor.getShort(offset + 1) != 0);
        entity.setFromUserName(cursor.getString(offset + 2));
        entity.setFromUserHeadImage(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setChatMessage(cursor.getString(offset + 4));
        entity.setMessageType(cursor.getInt(offset + 5));
        entity.setViewMessageType(cursor.getInt(offset + 6));
        entity.setSendTime(cursor.getLong(offset + 7));
        entity.setMessageQuiet(cursor.getShort(offset + 8) != 0);
        entity.setReaded(cursor.getShort(offset + 9) != 0);
        entity.setCount(cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10));
        entity.setMsgState(cursor.getInt(offset + 11));
        entity.setNotifyUser(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
     }
    
    @Override
    protected final Void updateKeyAfterInsert(ChatList entity, long rowId) {
        // Unsupported or missing PK type
        return null;
    }
    
    @Override
    public Void getKey(ChatList entity) {
        return null;
    }

    @Override
    public boolean hasKey(ChatList entity) {
        // TODO
        return false;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
