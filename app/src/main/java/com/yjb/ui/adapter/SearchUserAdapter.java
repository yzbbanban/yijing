package com.yjb.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.yjb.BuildConfig;
import com.yjb.R;
import com.yjb.dao.table.Friend;

import java.util.List;

/**
 * Created by kennysun on 2019/9/4.
 */

public class SearchUserAdapter extends BaseQuickAdapter<Friend, BaseMyViewHolder> {
    public SearchUserAdapter(int layoutResId, @Nullable List<Friend> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, Friend item) {
        helper.loadRoundImage(R.id.ivAvatar, "" + item.getHeadImage());
        String remark = item.getNickName();
        helper.setText(R.id.tvName, TextUtils.isEmpty(remark) ? item.getNickName() : remark);
    }
}
