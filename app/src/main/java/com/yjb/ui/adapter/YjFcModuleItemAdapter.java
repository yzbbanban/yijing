package com.yjb.ui.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.dian.commonlib.glide.GlideEngine;
import com.yjb.BuildConfig;
import com.yjb.R;
import com.yjb.mvp.model.bean.FengcaiList;

import java.util.List;

/**
 * Created by kennysun on 2019/9/2.
 */

public class YjFcModuleItemAdapter extends BaseQuickAdapter<FengcaiList.ListBean.FengcaiSetBean, BaseMyViewHolder> {
    public YjFcModuleItemAdapter(int layoutResId, @Nullable List<FengcaiList.ListBean.FengcaiSetBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, FengcaiList.ListBean.FengcaiSetBean item) {
        Log.i(TAG, "convert: " + item);

        ImageView imageView = helper.getView(R.id.ivModuleIcon);
        if (item.getPhotoimages() == null || item.getPhotoimages().length() == 0) {
            GlideEngine.load(imageView, R.drawable.contact_default_avatar);
        } else {
            GlideEngine.load(imageView, "" + item.getPhotoimages());
        }
    }
}
