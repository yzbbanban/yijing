package com.huohuo.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.huohuo.R;
import com.huohuo.mvp.model.bean.HomeRecomBean;

import java.util.List;

/**
 * Created by kennysun on 2019/8/30.
 */

public class HomeRecomAdapter extends BaseQuickAdapter<HomeRecomBean, BaseMyViewHolder> {
    public HomeRecomAdapter(int layoutResId, @Nullable List<HomeRecomBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, HomeRecomBean item) {
        helper.loadImage(R.id.ivIcon, R.drawable.temp);
    }
}
