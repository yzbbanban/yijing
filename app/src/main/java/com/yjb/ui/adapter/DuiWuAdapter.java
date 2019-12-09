package com.yjb.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.yjb.BuildConfig;
import com.yjb.R;
import com.yjb.dao.table.YiFcDetail;
import com.yjb.mvp.model.bean.YjTeam;

import java.util.List;

/**
 * Created by kennysun on 2019/9/5.
 */

public class DuiWuAdapter extends BaseQuickAdapter<YjTeam.ListBean.YjSetBean, BaseMyViewHolder> {

    public DuiWuAdapter(int layoutResId, @Nullable List<YjTeam.ListBean.YjSetBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, YjTeam.ListBean.YjSetBean item) {
        helper.setText(R.id.tvName, item.getNickname());
        String groupHead = item.getPhotoimage();
        if (TextUtils.isEmpty(groupHead)) {
            helper.loadImage(R.id.ivAvatar, R.drawable.group_icon);
        } else {
            helper.loadImage(R.id.ivAvatar, "" + groupHead);
        }

    }
}
