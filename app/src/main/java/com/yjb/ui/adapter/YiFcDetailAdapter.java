package com.yjb.ui.adapter;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.yjb.BuildConfig;
import com.yjb.R;
import com.yjb.mvp.model.bean.FengcaiList;

import java.util.List;

/**
 * Created by kennysun on 2019/9/5.
 */

public class YiFcDetailAdapter extends BaseQuickAdapter<FengcaiList.ListBean.FengcaiSetBean, BaseMyViewHolder> {

    public YiFcDetailAdapter(int layoutResId, @Nullable List<FengcaiList.ListBean.FengcaiSetBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, FengcaiList.ListBean.FengcaiSetBean item) {
        helper.setText(R.id.tvName, item.getPhototitle());
        String groupHead = item.getPhotoimages();
        if (TextUtils.isEmpty(groupHead)) {
            helper.loadImage(R.id.ivAvatar, R.drawable.group_icon);
        } else {
            helper.loadImage(R.id.ivAvatar, "" + groupHead);
        }

    }
}
