package com.yjb.dao.manager;

import com.dian.commonlib.utils.LogUtil;
import com.dian.commonlib.utils.SchedulerUtil;
import com.yjb.dao.listener.GroupChangeListener;
import com.yjb.dao.GroupDao;
import com.yjb.dao.table.Group;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by kennysun on 2019/8/8.
 */

public class GroupManager {
    GroupChangeListener mListener;

    public void setmListener(GroupChangeListener mListener) {
        this.mListener = mListener;
    }

    /**
     * 获取本地存储的所有群组
     */
    public Observable<List<Group>> selectList() {
        return Observable.just(DaoManager.getDaoManager().getDaoSession().getGroupDao().loadAll());
    }

    /**
     * 搜索
     * @param string
     * @return
     */
    public Observable<List<Group>> search(String string) {
        QueryBuilder<Group> friendsQueryBuilder = DaoManager.getDaoManager().getDaoSession().queryBuilder(Group.class);
        List<Group> list = friendsQueryBuilder.where(GroupDao.Properties.GroupName.like("%" + string + "%"))
                .list();
        return Observable.just(list);
    }
    /**
     * 插入所有群组
     */
    public void insertList(final List<Group> groups) {
        Observable.create(emitter -> {
            DaoManager.getDaoManager().getDaoSession().getGroupDao().insertOrReplaceInTx(groups);
            emitter.onNext("");
        }).compose(SchedulerUtil.ioToMain())
                .subscribe(o -> {
                    //存入成功
                }, throwable -> {
                    //存入失败
                    LogUtil.d("GroupManager insertList throwable=" + throwable);
                });
    }
}
