package com.huohuo.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.dian.commonlib.utils.ToastUtil;
import com.huohuo.R;
import com.huohuo.dao.table.ChatList;
import com.huohuo.dao.table.NewsData;

import java.util.List;

import q.rorbin.badgeview.Badge;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by kennysun on 2019/9/2.
 */

public class MsgListAdapter extends BaseQuickAdapter<NewsData, BaseMyViewHolder> {
    public MsgListAdapter(int layoutResId, @Nullable List<NewsData> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, NewsData item) {
        int i = getData().indexOf(item);
        helper.setText(R.id.tvMsgTarget, item.getTitle());
        helper.loadImage(R.id.ivAvatar, R.drawable.sys_notice);
        helper.setText(R.id.tvMsgTime, "新闻今天:" + item.getTime());
        helper.setText(R.id.tvAlreadyRead, item.getReadCount() + "已读");


    }
}
