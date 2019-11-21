package com.yjb.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.yjb.BuildConfig;
import com.yjb.R;
import com.yjb.dao.table.Group;

import java.util.List;

/**
 * Created by kennysun on 2019/9/5.
 */

public class GroupAdapter extends BaseQuickAdapter<Group, BaseMyViewHolder> {
    public GroupAdapter(int layoutResId, @Nullable List<Group> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, Group item) {
        helper.setText(R.id.tvName, item.getGroupName());
        String groupHead = item.getGroupAssignHead();//自定义头像
        if (TextUtils.isEmpty(groupHead)) {
            groupHead = item.getGroupAutoHead();//合成头像
        }
        if (TextUtils.isEmpty(groupHead)) {
            helper.loadImage(R.id.ivAvatar, R.drawable.group_icon);
        } else {
            helper.loadRoundImage(R.id.ivAvatar, BuildConfig.API_IMG_HOST + groupHead);
        }

    }
}
