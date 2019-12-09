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
import com.yjb.mvp.model.bean.YjTeam;

import java.util.List;

/**
 * Created by kennysun on 2019/9/2.
 */

public class YxYjModuleItemAdapter extends BaseQuickAdapter<YjTeam.ListBean.YjSetBean, BaseMyViewHolder> {
    public YxYjModuleItemAdapter(int layoutResId, @Nullable List<YjTeam.ListBean.YjSetBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, YjTeam.ListBean.YjSetBean item) {
        Log.i(TAG, "convert: " + item);

        ImageView imageView = helper.getView(R.id.ivModuleIcon);
        if (item.getPhotoimage() == null || item.getPhotoimage().length() == 0) {
            GlideEngine.load(imageView, R.drawable.contact_default_avatar);
        } else {
            GlideEngine.load(imageView, "" + item.getPhotoimage());
        }
        helper.setText(R.id.tvName, item.getNickname());
    }
}
