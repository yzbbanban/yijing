package com.huohuo.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.huohuo.BuildConfig;
import com.huohuo.R;
import com.huohuo.dao.table.OutsideDetail;
import com.huohuo.mvp.model.bean.ActivityList;
import com.huohuo.mvp.model.bean.YjActivityDetail;

import java.util.List;

/**
 * Created by kennysun on 2019/9/5.
 */

public class OutsideAdapter extends BaseQuickAdapter<YjActivityDetail, BaseMyViewHolder> {
    public OutsideAdapter(int layoutResId, @Nullable List<YjActivityDetail> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, YjActivityDetail item) {
        helper.setText(R.id.tvTitle, "" + item.getTitle());
        helper.setText(R.id.tvDetailAddress, "" + item.getAddress());
        helper.setText(R.id.tvDetailTime, "活动时间：" + item.getActivitystarttime_text());
        helper.setText(R.id.tvDetailLimitTime, "报名截止:" + item.getActivitystoptime_text());
        String groupHead = item.getCoverimage();//自定义头像
        if (TextUtils.isEmpty(groupHead)) {
            helper.loadImage(R.id.ivImage, R.drawable.group_icon);
        } else {
            helper.loadRoundImage(R.id.ivImage, BuildConfig.API_IMG_HOST + groupHead);
        }

    }
}
