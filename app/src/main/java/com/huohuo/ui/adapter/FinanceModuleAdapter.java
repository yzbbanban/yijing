package com.huohuo.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.huohuo.R;
import com.huohuo.mvp.model.bean.ModuleBean;

import java.util.List;

/**
 * Created by kennysun on 2019/9/2.
 */

public class FinanceModuleAdapter extends BaseQuickAdapter<ModuleBean, BaseMyViewHolder> {
    public FinanceModuleAdapter(int layoutResId, @Nullable List<ModuleBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseMyViewHolder helper, ModuleBean item) {
        RecyclerView recyclerview = helper.getView(R.id.recyclerview);
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        FinanceModuleItemAdapter financeModuleItemAdapter = new FinanceModuleItemAdapter(R.layout.item_finance_module_item, item.getModuleItems());
        recyclerview.setAdapter(financeModuleItemAdapter);
    }
}
