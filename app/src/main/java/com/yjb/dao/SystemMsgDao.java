package com.yjb.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.yjb.dao.table.SystemMsg;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "SYSTEM_MSG".
*/
public class SystemMsgDao extends AbstractDao<SystemMsg, String> {

    public static final String TABLENAME = "SYSTEM_MSG";

    /**
     * Properties of entity SystemMsg.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property MsgId = new Property(0, String.class, "msgId", true, "MSG_ID");
        public final static Property Id = new Property(1, Long.class, "id", false, "ID");
        public final static Property MessageType = new Property(2, Integer.class, "messageType", false, "MESSAGE_TYPE");
        public final static Property SendTime = new Property(3, Long.class, "sendTime", false, "SEND_TIME");
        public final static Property SystemType = new Property(4, Integer.class, "systemType", false, "SYSTEM_TYPE");
        public final static Property Title = new Property(5, String.class, "title", false, "TITLE");
        public final static Property Content = new Property(6, String.class, "content", false, "CONTENT");
        public final static Property Type = new Property(7, Integer.class, "type", false, "TYPE");
        public final static Property ShowCount = new Property(8, boolean.class, "showCount", false, "SHOW_COUNT");
    }


    public SystemMsgDao(DaoConfig config) {
        super(config);
    }
    
    public SystemMsgDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"SYSTEM_MSG\" (" + //
                "\"MSG_ID\" TEXT PRIMARY KEY NOT NULL ," + // 0: msgId
                "\"ID\" INTEGER NOT NULL ," + // 1: id
                "\"MESSAGE_TYPE\" INTEGER NOT NULL ," + // 2: messageType
                "\"SEND_TIME\" INTEGER NOT NULL ," + // 3: sendTime
                "\"SYSTEM_TYPE\" INTEGER NOT NULL ," + // 4: systemType
                "\"TITLE\" TEXT," + // 5: title
                "\"CONTENT\" TEXT," + // 6: content
                "\"TYPE\" INTEGER," + // 7: type
                "\"SHOW_COUNT\" INTEGER NOT NULL );"); // 8: showCount
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"SYSTEM_MSG\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, SystemMsg entity) {
        stmt.clearBindings();
 
        String msgId = entity.getMsgId();
        if (msgId != null) {
            stmt.bindString(1, msgId);
        }
        stmt.bindLong(2, entity.getId());
        stmt.bindLong(3, entity.getMessageType());
        stmt.bindLong(4, entity.getSendTime());
        stmt.bindLong(5, entity.getSystemType());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(6, title);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(7, content);
        }
 
        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(8, type);
        }
        stmt.bindLong(9, entity.getShowCount() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, SystemMsg entity) {
        stmt.clearBindings();
 
        String msgId = entity.getMsgId();
        if (msgId != null) {
            stmt.bindString(1, msgId);
        }
        stmt.bindLong(2, entity.getId());
        stmt.bindLong(3, entity.getMessageType());
        stmt.bindLong(4, entity.getSendTime());
        stmt.bindLong(5, entity.getSystemType());
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(6, title);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(7, content);
        }
 
        Integer type = entity.getType();
        if (type != null) {
            stmt.bindLong(8, type);
        }
        stmt.bindLong(9, entity.getShowCount() ? 1L: 0L);
    }

    @Override
    public String readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0);
    }    

    @Override
    public SystemMsg readEntity(Cursor cursor, int offset) {
        SystemMsg entity = new SystemMsg( //
            cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0), // msgId
            cursor.getLong(offset + 1), // id
            cursor.getInt(offset + 2), // messageType
            cursor.getLong(offset + 3), // sendTime
            cursor.getInt(offset + 4), // systemType
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // title
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // content
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // type
            cursor.getShort(offset + 8) != 0 // showCount
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, SystemMsg entity, int offset) {
        entity.setMsgId(cursor.isNull(offset + 0) ? null : cursor.getString(offset + 0));
        entity.setId(cursor.getLong(offset + 1));
        entity.setMessageType(cursor.getInt(offset + 2));
        entity.setSendTime(cursor.getLong(offset + 3));
        entity.setSystemType(cursor.getInt(offset + 4));
        entity.setTitle(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setContent(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setType(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setShowCount(cursor.getShort(offset + 8) != 0);
     }
    
    @Override
    protected final String updateKeyAfterInsert(SystemMsg entity, long rowId) {
        return entity.getMsgId();
    }
    
    @Override
    public String getKey(SystemMsg entity) {
        if(entity != null) {
            return entity.getMsgId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(SystemMsg entity) {
        return entity.getMsgId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
