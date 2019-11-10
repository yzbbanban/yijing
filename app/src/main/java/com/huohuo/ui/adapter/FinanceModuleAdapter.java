package com.huohuo.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.dian.commonlib.utils.ToastUtil;
import com.huohuo.R;
import com.huohuo.mvp.model.bean.ModuleBean;

import java.util.List;

/**
 * Created by kennysun on 2019/9/2.
 */

public class FinanceModuleAdapter extends BaseQuickAdapter<ModuleBean, BaseMyViewHolder> {

    private Context context;

    public FinanceModuleAdapter(int layoutResId, @Nullable List<ModuleBean> data) {
        super(layoutResId, data);
    }

    public FinanceModuleAdapter(int layoutResId, @Nullable List<ModuleBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseMyViewHolder helper, ModuleBean item) {
        RecyclerView recyclerview = helper.getView(R.id.recyclerview);
        helper.setText(R.id.tvTitle, item.getTitle());
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        FinanceModuleItemAdapter financeModuleItemAdapter;
        if (item.getType() == 1) {
            financeModuleItemAdapter = new FinanceModuleItemAdapter(R.layout.item_finance_module_type, item.getModuleItems());
        } else {
            financeModuleItemAdapter = new FinanceModuleItemAdapter(R.layout.item_finance_module_item, item.getModuleItems());
        }
        recyclerview.setAdapter(financeModuleItemAdapter);
    }
}
