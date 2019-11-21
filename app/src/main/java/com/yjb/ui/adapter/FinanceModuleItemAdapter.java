package com.yjb.ui.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.dian.commonlib.glide.GlideEngine;
import com.yjb.BuildConfig;
import com.yjb.R;
import com.yjb.mvp.model.bean.ModuleItemBean;

import java.util.List;

/**
 * Created by kennysun on 2019/9/2.
 */

public class FinanceModuleItemAdapter extends BaseQuickAdapter<ModuleItemBean, BaseMyViewHolder> {
    public FinanceModuleItemAdapter(int layoutResId, @Nullable List<ModuleItemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, ModuleItemBean item) {
        Log.i(TAG, "convert: " + item);

        ImageView imageView = helper.getView(R.id.ivModuleIcon);
        if (item.getPhotoUrl() == null || item.getPhotoUrl().length() == 0) {
            GlideEngine.load(imageView, R.drawable.contact_default_avatar);
        } else {
            GlideEngine.load(imageView, BuildConfig.API_IMG_HOST + item.getPhotoUrl());
        }
        int index = item.getIndex() == null ? 4 : item.getIndex();
        helper.setText(R.id.tvModuleText, item.getName());
        helper.setText(R.id.tvModuleOrder, "" + index);
        switch (index) {
            case 1:
                helper.setBackgroundRes(R.id.tvModuleOrder, R.drawable.order1);
                break;
            case 2:
                helper.setBackgroundRes(R.id.tvModuleOrder, R.drawable.order2);
                break;
            case 3:
                helper.setBackgroundRes(R.id.tvModuleOrder, R.drawable.order3);
                break;
            default:
                helper.setBackgroundRes(R.id.tvModuleOrder, R.drawable.orderother);
                break;
        }

    }
}
