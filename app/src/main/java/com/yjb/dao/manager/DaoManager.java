package com.yjb.dao.manager;

import com.dian.commonlib.app.Constants;
import com.dian.commonlib.utils.MmkvUtil;
import com.yjb.app.HuoHuoApp;
import com.yjb.dao.DaoSession;
import com.yjb.dao.MySQLiteOpenHelper;
import com.yjb.dao.DaoMaster;

/**
 * 数据库管理器
 * Created by kennysun on 2019/8/7.
 */

public class DaoManager {
    //创建数据库的名字
    private static String DB_NAME = "BTW";
    //创建数据库的工具
    private MySQLiteOpenHelper mHelper;
    //它里边实际上是保存数据库的对象,判断是否有存在数据库，如果没有则创建
    private DaoMaster mDaoMaster;
    //管理gen里生成的所有的Dao对象里边带有基本的增删改查的方法
    private DaoSession mDaoSession;
    //多线程中要被共享的使用volatile关键字修饰  GreenDao管理类
    volatile static private DaoManager mDaoManager;
    //群管理
    private GroupManager mGroupManager;
    private FriendManager mFriendManager;


    public static synchronized DaoManager getDaoManager() {
        synchronized (DaoManager.class) {
            if (mDaoManager == null) {
                mDaoManager = new DaoManager();
            }
        }
        return mDaoManager;
    }

    public DaoSession getDaoSession() {
        if (mDaoSession == null) {
            mDaoSession = getDaoMaster().newSession();
        }
        return mDaoSession;
    }

    public DaoMaster getDaoMaster() {
        if (mDaoMaster == null) {
            String dbName = createDbname();
            mHelper = new MySQLiteOpenHelper(
                    HuoHuoApp.mApp,
                    dbName,
                    null
            );
            mDaoMaster = new DaoMaster(mHelper.getWritableDatabase());
        }
        return mDaoMaster;
    }

    public GroupManager getGroupManager() {
        if (mGroupManager == null) {
            mGroupManager = new GroupManager();
        }
        return mGroupManager;
    }

    public FriendManager getFriendManager() {
        if (mFriendManager == null) {
            mFriendManager = new FriendManager();
        }
        return mFriendManager;
    }

    private String createDbname() {
        Long userId = MmkvUtil.decodeLong(Constants.USER_ID, -1l);
        String dbName = DB_NAME + userId + ".db";
        return dbName;
    }

    /**
     * 关闭所有的操作，数据库开启后，使用完毕要关闭
     */
    public void closeConnection() {
        if (mHelper != null)
            mHelper.close();
        if (mDaoSession != null)
            mDaoSession.clear();

        mHelper = null;
        mDaoSession = null;
        mDaoMaster = null;

        mGroupManager = null;

        mDaoManager = null;
    }
}
