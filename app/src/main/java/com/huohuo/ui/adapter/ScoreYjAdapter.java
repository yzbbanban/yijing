package com.huohuo.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.huohuo.BuildConfig;
import com.huohuo.R;
import com.huohuo.dao.table.OutsideDetail;
import com.huohuo.dao.table.ScoreYj;

import java.util.List;

/**
 * Created by kennysun on 2019/9/5.
 */

public class ScoreYjAdapter extends BaseQuickAdapter<ScoreYj, BaseMyViewHolder> {
    public ScoreYjAdapter(int layoutResId, @Nullable List<ScoreYj> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, ScoreYj item) {
        helper.setText(R.id.tvName, ""+item.getName());
        helper.setText(R.id.tvDetailRemark, ""+item.getRemark());
        helper.setText(R.id.tvDetailTime, "活动时间："+item.getTime());
        helper.setText(R.id.tvIndex, ""+item.getIndex());
        String groupHead = item.getPhotoUrl();//自定义头像
        if (TextUtils.isEmpty(groupHead)) {
            helper.loadImage(R.id.ivImage, R.drawable.group_icon);
        } else {
            helper.loadRoundImage(R.id.ivImage, BuildConfig.API_IMG_HOST + groupHead);
        }

    }
}
