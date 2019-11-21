package com.huohuo.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.huohuo.BuildConfig;
import com.huohuo.R;
import com.huohuo.dao.table.YiFcDetail;
import com.huohuo.mvp.model.bean.FengcaiList;

import java.util.List;

/**
 * Created by kennysun on 2019/9/5.
 */

public class DuiWuAdapter extends BaseQuickAdapter<YiFcDetail, BaseMyViewHolder> {

    public DuiWuAdapter(int layoutResId, @Nullable List<YiFcDetail> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, YiFcDetail item) {
        helper.setText(R.id.tvName, item.getTitle());
        String groupHead = item.getImage();
        if (TextUtils.isEmpty(groupHead)) {
            helper.loadImage(R.id.ivAvatar, R.drawable.group_icon);
        } else {
            helper.loadRoundImage(R.id.ivAvatar, BuildConfig.API_IMG_HOST + groupHead);
        }

    }
}