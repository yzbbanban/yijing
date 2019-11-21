package com.yjb.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.yjb.R;
import com.yjb.dao.table.FriendApply;

import java.util.List;

/**
 * Created by kennysun on 2019/9/3.
 */

public class NewFriendAdapter extends BaseQuickAdapter<FriendApply, BaseMyViewHolder> {
    public NewFriendAdapter(int layoutResId, @Nullable List<FriendApply> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, FriendApply item) {
        helper.loadRoundImage(R.id.ivAvatar, item.getAvatar())
                .setText(R.id.tvName, item.getNickName())
                .addOnClickListener(R.id.btAgree, R.id.btRefuse);
    }
}
