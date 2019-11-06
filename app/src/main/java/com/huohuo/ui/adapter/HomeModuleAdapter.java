package com.huohuo.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.huohuo.mvp.model.bean.ModuleBean;
import com.huohuo.mvp.model.bean.ModuleItemBean;

import java.util.List;

/**
 * Created by kennysun on 2019/8/30.
 */

public class HomeModuleAdapter extends BaseQuickAdapter<ModuleItemBean,BaseMyViewHolder> {
    public HomeModuleAdapter(int layoutResId, @Nullable List<ModuleItemBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, ModuleItemBean item) {

    }
}
