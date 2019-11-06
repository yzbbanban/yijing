package com.huohuo.dao.manager;

import android.text.TextUtils;

import com.dian.commonlib.utils.LogUtil;
import com.dian.commonlib.utils.SchedulerUtil;
import com.dian.commonlib.utils.ValidateUtil;
import com.huohuo.dao.FriendDao;
import com.huohuo.dao.table.Friend;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by kennysun on 2019/9/3.
 */

public class FriendManager {

    public void insertList(final List<Friend> friendBeans) {
        Observable.create(emitter -> {
            List<Friend> friends = handFriendList(friendBeans);
            DaoManager.getDaoManager().getDaoSession().getFriendDao().insertOrReplaceInTx(friends);
            emitter.onNext("");
        }).compose(SchedulerUtil.ioToMain())
                .subscribe(o -> {
                    //存入成功
                }, throwable -> {
                    //存入失败
                    LogUtil.d("FriendManager insertList throwable=" + throwable);
                });
    }

    /**
     * 获取本地存储的所有
     */
    public Observable<List<Friend>> selectList() {
        return Observable.just(DaoManager.getDaoManager().getDaoSession().getFriendDao().loadAll());
    }


    public List<Friend> handFriendList(final List<Friend> friendBeans) {
        List<Friend> friends = new ArrayList<>();
        for (Friend friendBean : friendBeans) {
            //处理用户昵称或备注，转成对应的大写拼音
            String remark = friendBean.getFriendRemark();
            String nickName = friendBean.getNickName();
            String name = TextUtils.isEmpty(remark) ? nickName : remark;
            String letter;
            if (TextUtils.isEmpty(name)) {
                name = "#";
            }
            letter = ValidateUtil.getFirstChar(name);
            friendBean.setFirstChar(letter);
            friends.add(friendBean);
        }
        return friends;
    }


    /**
     * 搜索
     * @param string
     * @return
     */
    public Observable<List<Friend>> search(String string) {
        QueryBuilder<Friend> friendsQueryBuilder = DaoManager.getDaoManager().getDaoSession().queryBuilder(Friend.class);
        List<Friend> list = friendsQueryBuilder.where(friendsQueryBuilder.or(FriendDao.Properties.Mobile.like("%" + string + "%"),
                FriendDao.Properties.FriendRemark.like("%" + string + "%"), FriendDao.Properties.NickName.like("%" + string + "%")))
                .orderAsc(FriendDao.Properties.FirstChar)
                .list();
        return Observable.just(list);
    }
}
