package com.yjb.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.dian.commonlib.utils.DateFormatUtil;
import com.yjb.BuildConfig;
import com.yjb.R;
import com.yjb.mvp.model.bean.AcMyList;

import java.util.List;

/**
 * Created by kennysun on 2019/9/5.
 */

public class MyOutsideAdapter extends BaseQuickAdapter<AcMyList.ListBean, BaseMyViewHolder> {
    public MyOutsideAdapter(int layoutResId, @Nullable List<AcMyList.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, AcMyList.ListBean item) {
        helper.setText(R.id.tvTitle, "" + item.getTitle());
        helper.setText(R.id.tvDetailAddress, "" + item.getAddress());
        helper.setText(R.id.tvDetailTime, "活动时间：" + DateFormatUtil.timeStamp2Date("" + item.getActivitystarttime()));
        helper.setText(R.id.tvDetailLimitTime, "报名截止:" + DateFormatUtil.timeStamp2Date("" + item.getActivitystopapplytime()));
        String groupHead = item.getCoverimage();//自定义头像
        if (TextUtils.isEmpty(groupHead)) {
            helper.loadImage(R.id.ivImage, R.drawable.group_icon);
        } else {
            helper.loadImage(R.id.ivImage, BuildConfig.API_IMG_HOST + groupHead);
        }

    }
}
