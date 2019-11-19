package com.huohuo.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.huohuo.BuildConfig;
import com.huohuo.R;
import com.huohuo.dao.table.OutsideDetail;

import java.util.List;

/**
 * Created by kennysun on 2019/9/5.
 */

public class MyOutsideAdapter extends BaseQuickAdapter<OutsideDetail, BaseMyViewHolder> {
    public MyOutsideAdapter(int layoutResId, @Nullable List<OutsideDetail> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, OutsideDetail item) {
        helper.setText(R.id.tvTitle, "" + item.getTitle());
        helper.setText(R.id.tvDetailAddress, "" + item.getAddress());
        helper.setText(R.id.tvDetailTime, "活动时间：" + item.getTime());
        helper.setText(R.id.tvDetailLimitTime, "报名截止:" + item.getLimitTime());
        String groupHead = item.getImage();//自定义头像
        if (TextUtils.isEmpty(groupHead)) {
            helper.loadImage(R.id.ivImage, R.drawable.group_icon);
        } else {
            helper.loadRoundImage(R.id.ivImage, BuildConfig.API_IMG_HOST + groupHead);
        }

    }
}
