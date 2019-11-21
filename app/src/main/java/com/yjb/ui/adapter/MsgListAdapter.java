package com.yjb.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.dian.commonlib.glide.GlideEngine;
import com.yjb.BuildConfig;
import com.yjb.R;
import com.yjb.mvp.model.bean.NewsList;

import java.util.List;

/**
 * Created by kennysun on 2019/9/2.
 */

public class MsgListAdapter extends BaseQuickAdapter<NewsList.ListBean, BaseMyViewHolder> {
    public MsgListAdapter(int layoutResId, @Nullable List<NewsList.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, NewsList.ListBean item) {
        helper.setText(R.id.tvMsgTarget, item.getTitle());
        ImageView imageView = helper.getView(R.id.ivAvatar);
        if (item.getCoverimage() == null || item.getCoverimage().length() == 0) {
            GlideEngine.load(imageView, R.drawable.contact_default_avatar);
        } else {
            GlideEngine.load(imageView, BuildConfig.API_IMG_HOST + item.getCoverimage());
        }
        helper.setText(R.id.tvMsgTime, "新闻今天:" + item.getCreatetime_text());
        helper.setText(R.id.tvAlreadyRead, item.getView() + "已读");


    }
}
