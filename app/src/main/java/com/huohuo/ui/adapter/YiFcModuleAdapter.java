package com.huohuo.ui.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.dian.commonlib.base.BaseMyViewHolder;
import com.huohuo.R;
import com.huohuo.mvp.model.bean.FengcaiList;
import com.huohuo.mvp.model.bean.ModuleBean;

import java.util.List;

/**
 * Created by kennysun on 2019/9/2.
 */

public class YiFcModuleAdapter extends BaseQuickAdapter<FengcaiList.ListBean, BaseMyViewHolder> {

    private Context context;

    public YiFcModuleAdapter(int layoutResId, @Nullable List<FengcaiList.ListBean> data) {
        super(layoutResId, data);
    }

    public YiFcModuleAdapter(int layoutResId, @Nullable List<FengcaiList.ListBean> data, Context context) {
        super(layoutResId, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseMyViewHolder helper, FengcaiList.ListBean item) {
        RecyclerView recyclerview = helper.getView(R.id.rvYiFc);
        helper.setText(R.id.tvYiFcTitle, item.getTitle());
        recyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        YjFcModuleItemAdapter yjFcModuleItemAdapter;
        yjFcModuleItemAdapter = new YjFcModuleItemAdapter(R.layout.item_yjfc_module_type, item.getFengcai_set());
        recyclerview.setAdapter(yjFcModuleItemAdapter);
    }
}
