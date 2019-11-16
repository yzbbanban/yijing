package com.huohuo.ui.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.dian.commonlib.glide.GlideEngine;
import com.huohuo.R;
import com.huohuo.mvp.model.bean.ModuleItemBean;

import java.util.List;

/**
 * Created by kennysun on 2019/9/2.
 */

public class YjFcModuleItemAdapter extends BaseQuickAdapter<ModuleItemBean, BaseMyViewHolder> {
    public YjFcModuleItemAdapter(int layoutResId, @Nullable List<ModuleItemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, ModuleItemBean item) {
        Log.i(TAG, "convert: " + item);

        ImageView imageView = helper.getView(R.id.ivModuleIcon);
        if (item.getPhotoUrl() == null || item.getPhotoUrl().length() == 0) {
            GlideEngine.load(imageView, R.drawable.contact_default_avatar);
        } else {
            GlideEngine.load(imageView, item.getPhotoUrl());
        }
    }
}
